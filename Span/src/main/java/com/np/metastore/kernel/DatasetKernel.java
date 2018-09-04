package com.np.metastore.kernel;

import com.np.metastore.data.Dataset;
import com.np.metastore.data.DatasetSize;
import com.np.metastore.management.SystemException;
import com.np.spark.kernel.Util;
import com.np.util.Config;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import org.apache.commons.lang.StringUtils;
import org.apache.spark.Partition;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;
import parquet.org.codehaus.jackson.map.ObjectMapper;
import parquet.org.codehaus.jackson.map.ObjectWriter;

import java.io.*;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DatasetKernel {

    private static Dataset getByTable(String tableName) throws SQLException {
        Dataset result = null;
        PreparedStatement stmt = Config.getInstance().getConn()
                .prepareStatement("SELECT dataset_id, dataset_name, dataset_url, dataset_table, dataset_query, dataset_description, dataset_info, dataset_timestamp, dataset_user, dataset_size FROM aa_dataset WHERE dataset_table=?");
        stmt.setString(1, tableName);
        ResultSet res = stmt.executeQuery();
        if (res.next()) {
            result = new Dataset(res);
        }
        stmt.close();
        return result;
    }

    public static void validateDataset(Dataset data)
    {
        if (data.getUrl()==null)
            throw new SystemException("Dataset URL cannot be null");

        if (data.getTable()==null)
            throw new SystemException("Dataset table cannot be null");

        if (data.getName() == null)
            throw new SystemException("Dataset name cannot be null");
    }

    private static String datasetSize(String url) throws IOException, URISyntaxException {
        final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        DatasetSize ds = Core.datasetSize(url);
        String responseJSON = ow.writeValueAsString(ds);
        return responseJSON;
    }

    public static Dataset create(Dataset data) throws SQLException, IOException, URISyntaxException {
        boolean dropTempTable = false;

        data.setId(Config.getShortUUID());
        data.setTimestamp(System.currentTimeMillis());

        validateDataset(data);

        try {
            System.out.println("data=" + data.toString());

            // Check that specified table name not used
            Dataset existingDS = getByTable(data.getTable());
            if (existingDS!=null)
                throw new SystemException("Table " + data.getTable() + " already exists");

            // register dataset
            Util.registerTempTable(data.getUrl(), data.getTable());
            dropTempTable = true; // temp table has been created, we should drop it if something goes wrong

            data.setSize(datasetSize(data.getUrl()));

            // create new dataset in metastore db
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("INSERT INTO aa_dataset (dataset_id, dataset_name, dataset_url, dataset_table, dataset_query, dataset_description, dataset_info, dataset_timestamp, dataset_user, dataset_size) VALUES(?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, data.getId());
            stmt.setString(2, data.getName());
            stmt.setString(3, data.getUrl());
            stmt.setString(4, data.getTable());
            stmt.setString(5, data.getQuery());
            stmt.setString(6, data.getDescription());
            stmt.setString(7, data.getInfo());
            stmt.setLong(8, data.getTimestamp());
            stmt.setString(9, data.getUser());
            stmt.setString(10, data.getSize());
            stmt.executeUpdate();

            stmt.close();

            // commit
            Config.getInstance().getConn().commit();

        } catch (Throwable e) {
            if (dropTempTable)
                Util.dropTempTable(data.getTable()); // drop temp table if we cannot register dataset or other problems occurs
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
        return data;

    }

    public static Dataset update(Dataset data) throws SQLException, IOException, URISyntaxException {

        validateDataset(data);

        Dataset currentDS = get(data.getId());
        if (currentDS==null)
            throw new SystemException("Dataset not found");

        boolean updateTableRegistration = false;
        if (!data.getTable().equals(currentDS.getTable()))
            updateTableRegistration = true;
        if (!data.getUrl().equals(currentDS.getUrl()))
            updateTableRegistration = true;

        try {
            System.out.println("data=" + data.toString());

            if (updateTableRegistration) {
                Util.dropTempTable(currentDS.getTable());
                Util.registerTempTable(data.getUrl(), data.getTable());
            }

            data.setSize(datasetSize(data.getUrl()));

            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("UPDATE aa_dataset SET dataset_name=?, dataset_url=?, dataset_table=?, dataset_query=?, dataset_description=?, dataset_info=?, dataset_size=? WHERE dataset_id=?");
            stmt.setString(1, data.getName());
            stmt.setString(2, data.getUrl());
            stmt.setString(3, data.getTable());
            stmt.setString(4, data.getQuery());
            stmt.setString(5, data.getDescription());
            stmt.setString(6, data.getInfo());
            stmt.setString(7, data.getSize());
            stmt.setString(8, data.getId());
            if (stmt.executeUpdate() == 0)
                throw new ItemNotFound(data.getId());

            stmt.close();
            Config.getInstance().getConn().commit();
            return data;
        } catch (Exception e) {
            if (updateTableRegistration) {
                Util.dropTempTable(data.getTable());
                Util.registerTempTable(currentDS.getUrl(), currentDS.getTable());
            }

            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static String delete(String id) throws SQLException {
        // de-register existing dataset
        Dataset currentDS = get(id);
        if (currentDS==null)
            throw new SystemException("Dataset not found");

        if (currentDS.getTable()!=null)
            Util.dropTempTable(currentDS.getTable());

        int ret = 0;
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("DELETE FROM aa_dataset WHERE dataset_id=?");
            stmt.setString(1, id);
            ret = stmt.executeUpdate();
            stmt.close();
            Config.getInstance().getConn().commit();
            return String.valueOf(ret);
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static Dataset get(String id) throws SQLException {
        Dataset result = null;
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT dataset_id, dataset_name, dataset_url, dataset_table, dataset_query, dataset_description, dataset_info, dataset_timestamp, dataset_user, dataset_size FROM aa_dataset WHERE dataset_id=?");
            stmt.setString(1, id);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                result = new Dataset(res);
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Throwable e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static void exportToCSV(Writer w, Dataset ds) throws SQLException, IOException {
        final HiveContext hiveContext = Config.getInstance().getHiveContext();
        DataFrame resultSet = hiveContext.sql("SELECT * FROM " + ds.getTable());
        String[] columns = resultSet.columns();

        CsvWriterSettings settings = new CsvWriterSettings();
        settings.setMaxCharsPerColumn(1000000);
        settings.setMaxColumns(columns.length);
        settings.setHeaders(columns);

        CsvWriter writer = new CsvWriter(w, settings);
        writer.writeHeaders();
        writer.flush();

        // Now convert to JavaRDD to collect one partitions in a time
        JavaRDD<Row> rddRow = resultSet.javaRDD();
        for (Partition lpi : rddRow.partitions()) {
            List<Row> rowsInPartition[] = rddRow.collectPartitions(new int[]{lpi.index()});
            for (Row row : rowsInPartition[0]) {
                if (columns.length != row.size()) {
                    System.out.println("Skipping row: " + row.toString());
                    continue;
                }

                Object[] list = new Object[row.size()];

                for (int j = 0; j < row.size(); j++) {
                    list[j] = row.get(j);
                }
                writer.writeRow(list);
            }
            writer.flush();
        }
    }


    public static String getSample(String id) throws SQLException, IOException {
        Dataset ds = get(id);

        final HiveContext hiveContext = Config.getInstance().getHiveContext();
        DataFrame resultSet = hiveContext.sql("SELECT * FROM " + ds.getTable());
        long resultSetCount = resultSet.count();

        int colCount = resultSet.columns().length;

        //  Например, если колонок меньше 25, возвращать 20 строк; 25-100 колонок - 10 строк;
        // больше 100 - 2 строки.

        int rowCount = 20;
        if (colCount<25)
            rowCount=20;
        else if (colCount>=25 && colCount<100)
            rowCount=10;
        else if (colCount>=100)
            rowCount=2;

        double fraction = (double) (rowCount * 2) / resultSetCount;
        if (fraction > 1)
            fraction = 1;
        Row[] result = resultSet.sample(false, fraction).head(rowCount);

        final ArrayList<Object[]> dataList = new ArrayList<>(rowCount + 1);

        dataList.add(resultSet.columns());

        int cols = resultSet.columns().length;

        for (int i = 0; i < result.length; i++) {
            Object[] data = new Object[cols];
            for (int j = 0; j < cols; j++) {
                data[j] = result[i].get(j);
            }
            dataList.add(data);
        }

        final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        StringWriter w = new StringWriter(10000);
        ow.writeValue(w, dataList);
        w.close();
        return w.toString();
    }

    public static Dataset snapshot(Dataset dataset) throws SQLException, IOException, URISyntaxException {
        if (dataset.getQuery()==null || dataset.getQuery().equals(""))
            throw new SystemException("Query is empty");

        if (dataset.getUrl()==null || dataset.getUrl().equals(""))
            throw new SystemException("Url is empty");

        final HiveContext hiveContext = Config.getInstance().getHiveContext();
        DataFrame resultSet = hiveContext.sql(dataset.getQuery());

        resultSet.saveAsParquetFile(dataset.getUrl());

        dataset = DatasetKernel.create(dataset);

        return dataset;
    }

}
