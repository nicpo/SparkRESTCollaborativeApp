package com.np.spark.kernel;

import com.np.metastore.data.Dataset;
import com.np.metastore.kernel.Core;
import com.np.metastore.management.SystemException;
import com.np.util.Config;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

public class Util implements Serializable {

    public static String reFormatDecisionTree(String tree)
    {
        StringBuilder sb = new StringBuilder();

        String[] treeArr = tree.split("\n");
        int[] spaces = new int[treeArr.length];

        for (int i=0;i<treeArr.length;i++) {
            spaces[i] = treeArr[i].length() - treeArr[i].trim().length();

            for (int k = (i>0 ? spaces[i-1] : 0) - 1 ; k > spaces[i] ; k-=1)
                sb.append(getPrefixedString(k, "End If"));

            sb.append(getPrefixedString(spaces[i], treeArr[i].trim()));

        }

        for (int k = spaces[treeArr.length - 1] - 1 ; k > 1 ; k-=1)
            sb.append(getPrefixedString(k, "End If"));

        return sb.toString();
    }

    /**
     * Return text at required position, with spaces and | marks
     * @param pos
     * @param text
     * @return
     */
    private static String getPrefixedString(int pos, String text)
    {
        StringBuilder sb = new StringBuilder();

        if (pos==0) { // no offset - print as is
            sb.append(text);
            sb.append("\n");
            return sb.toString();
        }

        sb.append("  ");

        // then print vertical lines
        for (int j = 2; j < pos; j++)
            sb.append("|   ");

        // Calc drilldown character
        String chr;
        if (text.equals("End If"))
            chr = "`-- ";
        else if (text.startsWith("Predict: "))
            chr = "`-> ";
        else
            chr = "+-- ";

        sb.append(chr + text);
        sb.append("\n");

        return sb.toString();
    }

    public static void registerTempTable(String url, String table) throws FileNotFoundException, URISyntaxException, SystemException {
        HiveContext hiveContext = Config.getInstance().getHiveContext();
        System.out.println("Loading and registering parquet file " + url);
        File dir = Paths.get(new URI(url)).toFile();

        if (!dir.canRead())
            throw new SystemException("Cannot read directory " + url);

        if (!dir.isDirectory())
            throw new SystemException(url + " must be a directory");

        String[] fileList = dir.list();
        if (fileList==null || fileList.length==0)
            throw new SystemException("Directory " + url + " is empty");

        DataFrame parquetFile = hiveContext.parquetFile(url);
        parquetFile.registerTempTable(table);
    }

    public static void dropTempTable(String table)
    {
        try {
            HiveContext hiveContext = Config.getInstance().getHiveContext();
            System.out.println("Dropping table " + table);
            hiveContext.dropTempTable(table);
        } catch (Throwable ex) {
            System.out.println(ex.getMessage()); // we expect something like java.lang.RuntimeException: Table Not Found: twitter1 here and ignore it
        }
    }

    public static void registerAllDatasets() throws SQLException
    {
        ArrayList<Dataset> list = Core.listDataset();
        for (Dataset ds : list) {
            try {
                registerTempTable(ds.getUrl(), ds.getTable());
            } catch (SystemException|FileNotFoundException|URISyntaxException fnfe) {
                System.out.println("Cannot register dataset " + ds.getUrl() + ", skipping. Error " + fnfe.getMessage());
            }

        }

    }

    /**
     * Convert all data types to double. This version return 0 in case of exception,
     * for statistics where we cannot skip row, like correlation
     * @param row
     * @param i
     * @return
     */
    public static double getAsDouble(Row row, int i)
    {
        try {
            return getAsDoubleExceptionUnhandled(row, i);
        } catch (NumberFormatException ex) {
            // System.out.println("Parsing error for " + row.toString() + " i = " + i + " " + ex.getMessage());
            return 0;
        }
    }

    /**
     * Convert all data types to double. This version return don't catch any exceptions
     * to allow caller remove row
     * @param row
     * @param i
     * @return
     */
    public static double getAsDoubleExceptionUnhandled(Row row, int i)
    {
        if (row.isNullAt(i))
            return 0;

        String type = row.schema().fields()[i].dataType().typeName();

        try {
            if (type.equals("integer"))
                return row.getInt(i);
            else if (type.equals("string")) {
                String val = row.getString(i);
                return val.isEmpty() ? 0 : Double.valueOf(val);
            }
            else if (type.equals("double"))
                return row.getDouble(i);
            else if (type.equals("float"))
                return row.getFloat(i);
            else if (type.equals("long"))
                return row.getLong(i);
            throw new NumberFormatException("Cannot detect type " + type);
        } catch (NumberFormatException ex) {
            // System.out.println("Parsing error for " + row.toString() + " i = " + i + " " + ex.getMessage());
            return 0;
        }
    }

}
