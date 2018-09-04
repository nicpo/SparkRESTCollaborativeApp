package com.np.metastore.kernel;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.np.metastore.data.CSVFormat;
import com.np.metastore.data.CSVImportResponse;
import com.np.metastore.data.FieldType;
import com.np.util.Config;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import parquet.org.codehaus.jackson.map.ObjectMapper;
import parquet.org.codehaus.jackson.map.ObjectWriter;

import java.io.*;
import java.util.*;

public class ImportKernel {
    public static S3ObjectInputStream loadS3(String bucketName, String key) throws IOException {
        AmazonS3 s3Client = Config.getInstance().getS3Client();
        GetObjectRequest rangeObjectRequest = new GetObjectRequest(bucketName, key);

        // rangeObjectRequest.setRange(0, 10);
        S3Object objectPortion = s3Client.getObject(rangeObjectRequest);
        return objectPortion.getObjectContent();
    }


    public static void displayTextInputStream(InputStream input)
            throws IOException {
        // Read one text line at a time and display.
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(input));
        int counter = 0;
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            // System.out.println("    " + line);
            counter++;
            if (counter == 10)
                break;
        }
        // System.out.println();
    }

    public static String sample(CSVFormat format, InputStream is) throws IOException {
        CsvParser csvParser = new CsvParser(getCsvParserSettings(format));

        // open stream
        BufferedReader br = new BufferedReader(new InputStreamReader(new BOMInputStream(is)));

        if (format.isWithHeader()) // skip header
            br.readLine();

        final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        final ArrayList<String[]> data = new ArrayList<>(20);

        String strLine;
        int counter = 0;
        while ((strLine = br.readLine()) != null) {
            String[] row = csvParser.parseLine(strLine);
            if (row == null)
                continue;
            data.add(row);


            if (counter++ == 20)
                break;
        }

        StringWriter w = new StringWriter(10000);
        ow.writeValue(w, data);
        w.close();
        return w.toString();
    }

    public static CSVImportResponse createParquet(final CSVFormat format, InputStream is, int partitionSize, String[] columns, String parquetURL) throws IOException {
        // open stream
        BufferedReader br = new BufferedReader(new InputStreamReader(new BOMInputStream(is)));

        if (format.isWithHeader()) // skip header
            br.readLine();

        // create schema
        final String[] fieldTypes = format.getTypes();
        final String[] fieldNames = format.getHeaders();

        List<StructField> fields = new ArrayList<StructField>();
        for (int i = 0; i < fieldTypes.length; i++) {
            String fieldType = fieldTypes[i];
            DataType dt;
            if (fieldType.equals("DOUBLE"))
                dt = DataTypes.DoubleType;
            else if (fieldType.equals("LONG"))
                dt = DataTypes.LongType;
            else
                dt = DataTypes.StringType;

            // if CSV file has no header, we detect this case and generate field names like f0, f1, f2, f3,...
            String fieldName;
            if (fieldNames != null && fieldNames[i] != null)
                fieldName = fieldNames[i];
            else
                fieldName = "f" + i;
            fields.add(DataTypes.createStructField(fieldName, dt,true));
        }
        StructType schema = DataTypes.createStructType(fields);

        List<String> paths = new ArrayList<>();

        // create list of rows
        CSVLineParser lineParser = new CSVLineParser(getCsvParserSettings(format), fieldTypes);

        int key=0;
        String strLine;
        while ((strLine = br.readLine()) != null) {
            lineParser.submitLine(strLine);
            if (lineParser.getSize()==partitionSize) {
                String path = storeToParquetPartition(schema, lineParser.getRows(), columns, parquetURL, key++);
                paths.add(path);
                lineParser.clearTaskList();
            }
        }

        // store left items
        String path = storeToParquetPartition(schema, lineParser.getRows(), columns, parquetURL, key++);
        paths.add(path);
        lineParser.clearTaskList();

        lineParser.stop();

        //final SQLContext sqlCtx = Config.getInstance().getHiveContext();
        //DataFrame df = sqlCtx.parquetFile(paths.toArray(new String[]{}));
        //df.printSchema();
        //df.saveAsParquetFile(parquetURL);

        return new CSVImportResponse(lineParser.getFailedLines(10), lineParser.getReceivedLines(), lineParser.getProcessedLines());
    }

    private static String storeToParquetPartition(StructType schema, List<Row> list, String[] columns, String parquetURL, int key)
    {
        final JavaSparkContext ctx = Config.getInstance().getSparkContext();
        JavaRDD<Row> rowRDD = ctx.parallelize(list,4); // more partitions - more RAM usage, it cannot process twitter3 with 8 partitions here

        // apply schema and save parquet file
        final HiveContext hiveContext = Config.getInstance().getHiveContext();
        DataFrame df;

        if (columns==null || columns.length==0)
            df = hiveContext.createDataFrame(rowRDD, schema);
        else {
            // we should split columns to 2 parts: first element and array of others
            String[] columns2 = new String[columns.length-1];
            for (int i=1;i<columns.length;i++) {
                columns2[i-1]=columns[i];
            }
            df = hiveContext.createDataFrame(rowRDD, schema).select(columns[0],columns2);
        }


        String fullURL = parquetURL+"/key="+key;
        System.out.println("Store " + fullURL);
        df.saveAsParquetFile(fullURL);
        return fullURL;
    }


    public static CSVFormat guessFormat(InputStream is) throws IOException {

        CSVFormat result = new CSVFormat();

        // open stream
        BufferedReader br = new BufferedReader(new InputStreamReader(new BOMInputStream(is)));

        // read first line, it's header
        String headerString = br.readLine().trim();

        // guess delimiter based on header content
        char delimiter = guessDelimiter(headerString);
        result.setDelimiter(delimiter);

        // create parser with required delimiter
        CsvParserSettings settings = new CsvParserSettings();
        settings.getFormat().setLineSeparator("\n");
        settings.getFormat().setDelimiter(delimiter);
        settings.setMaxCharsPerColumn(1000000);
        settings.setMaxColumns(10000);
        settings.setHeaderExtractionEnabled(false);
        CsvParser parser = new CsvParser(settings);

        // parse header to get columnNames
        String[] columnNames = parser.parseLine(headerString);
        // now check if column names really looks like column names
        if (validateColumnNames(columnNames)) {
            result.setHeaders(columnNames);
            result.setWithHeader(true);
        } else
            result.setWithHeader(false);

        // create structure used to guess field type
        CSVTypeDetector[] column = initColumns(columnNames);

        String strLine;

        int counter = 0;
        while ((strLine = br.readLine()) != null) {
            // System.out.println (strLine);

            String[] row = parser.parseLine(strLine);
            if (row == null)
                continue;

            if (row.length > columnNames.length)
                continue;

            // update data types
            for (int i = 0; i < row.length; i++) {
                if (row[i] != null)
                    column[i].apply(row[i].trim());
            }

            if (counter++ == 200)
                break;
        }

        // store field types
        String[] columnTypes = new String[columnNames.length];
        for (int j = 0; j < columnNames.length; j++) {
            FieldType type = column[j].getType(0.01);
            if (type == FieldType.DOUBLE)
                columnTypes[j] = "DOUBLE";
            else if (type == FieldType.LONG)
                columnTypes[j] = "LONG";
            else
                columnTypes[j] = "STRING";
        }

        result.setTypes(columnTypes);

        parser.stopParsing();

        return result;
    }

    private static boolean validateColumnNames(String[] columnNames) {
        for (int i = 0; i < columnNames.length; i++)
            if (columnNames[i] == null || columnNames[i].equals(""))
                return false; // header cannot have empty columns

        for (int i = 0; i < columnNames.length; i++) {
            CSVTypeDetector td = new CSVTypeDetector();
            td.apply(columnNames[i]);
            FieldType type = td.getType(0.01);
            if (type == FieldType.LONG || type == FieldType.DOUBLE)
                return false; // we cannot use numbers as column name, cannot use them in SQL anyway
        }

        Set<String> mySet = new HashSet<String>(Arrays.asList(columnNames));
        if (mySet.size() != columnNames.length)
            return false; // header cannot have duplicate names

        // all check passed, this is likely header
        return true;
    }

    private static CSVTypeDetector[] initColumns(String[] header) {
        CSVTypeDetector[] result = new CSVTypeDetector[header.length];
        for (int i = 0; i < header.length; i++)
            result[i] = new CSVTypeDetector();
        return result;

    }

    private static char guessDelimiter(String header) throws IOException {
        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;

        // calc number of each possible delimiter in the header line
        for (int i = 0; i < header.length(); i++) {
            char c = header.charAt(i);
            if (c == ',')
                cnt1++;
            else if (c == '\t')
                cnt2++;
            else if (c == '|')
                cnt3++;
        }

        int max = Math.max(cnt1, Math.max(cnt2, cnt3));
        if (cnt1 == max)
            return ',';
        else if (cnt2 == max)
            return '\t';
        else if (cnt3 == max)
            return '|';

        throw new IOException("Cannot detect delimiter: " + header);
    }

    private static CsvParserSettings getCsvParserSettings(CSVFormat format) {
        CsvParserSettings settings = new CsvParserSettings();
        settings.getFormat().setLineSeparator("\n");
        settings.getFormat().setDelimiter(format.getDelimiter());
        settings.setMaxCharsPerColumn(1000000);
        settings.setMaxColumns(10000);
        settings.setHeaderExtractionEnabled(false); // automatic header extraction MUST be disable, otherwise parseLine will return null every first call
        return settings;
    }


}
