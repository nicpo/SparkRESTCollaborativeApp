package com.np;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.common.processor.BeanProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Convert<T> {
    private final String from;
    private final CsvParser parser;
    private final BeanProcessor<T> processor;


    public Convert(String from, String to, Class clazz) {
        this.from = from;

        processor = new ParquetProcessor<T>(clazz, to);

        CsvParserSettings settings = new CsvParserSettings();
        settings.getFormat().setLineSeparator("\n");
        settings.getFormat().setDelimiter(',');
        settings.setMaxCharsPerColumn(1000000);
        settings.setMaxColumns(1000);
        settings.setRowProcessor(processor);
        settings.setHeaderExtractionEnabled(true);
        parser = new CsvParser(settings);
    }

    public void process() throws IOException {
        FileReader fileReader = new FileReader(from);
        parser.parse(fileReader);
        fileReader.close();
    }

}
