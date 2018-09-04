package com.np;

import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.BeanProcessor;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.storage.StorageLevel;

import java.util.ArrayList;

public class ParquetProcessor<T> extends BeanProcessor<T> {
    private final String to;
    private final Class clazz;
    private final JavaSparkContext ctx = getJavaSparkContext();
    private final SQLContext sqlContext = new SQLContext(ctx);
    private ArrayList<T> buffer = new ArrayList<T>(10000);
    private ArrayList<JavaRDD<T>> rdds = new ArrayList<>();
    private JavaRDD<T> finRDD = ctx.emptyRDD();

    public ParquetProcessor(Class<T> beanType, String to) {
        super(beanType);
        this.clazz = beanType;
        this.to = to;
    }

    private JavaSparkContext getJavaSparkContext() {
        final SparkConf sparkConf = new SparkConf().setAppName("DataTool");
        sparkConf.setExecutorEnv("spark.executor.memory", "10g");
        sparkConf.setExecutorEnv("spark.storage.memoryFraction","0.1");
        sparkConf.setMaster("local[*]");
        JavaSparkContext c = new JavaSparkContext(sparkConf);
        return c;
    }

    @Override
    public void beanProcessed(T bean, ParsingContext context) {
        buffer.add(bean);
        if (buffer.size() == 10000)
            flushBufferToStore();
    }

    private void flushBufferToStore() {
        JavaRDD<T> rdd = ctx.parallelize(buffer).persist(StorageLevel.MEMORY_AND_DISK_SER());
        // finRDD = finRDD.union(rdd).persist(StorageLevel.MEMORY_AND_DISK_SER());
        rdds.add(rdd);

        buffer = new ArrayList(10000);
        System.out.print(".");
        // System.out.println("Store size = " + store.count());
    }

    @Override
    public void processStarted(ParsingContext context) {
        super.processStarted(context);
    }

    @Override
    public void processEnded(ParsingContext context) {
        super.processEnded(context);
        flushBufferToStore();

/*        System.out.println("Union...");
        JavaRDD<T> finRDD = ctx.emptyRDD();

        for (JavaRDD<T> item : rdds) {
            //System.out.println("Before:" + finRDD.collect().size());
            //System.out.println("Item:" + item.collect().size());

            System.out.println("done ");
            finRDD = finRDD.union(item).persist(StorageLevel.MEMORY_AND_DISK());
            //System.out.println("After:" + finRDD.collect().size());
        }
*/
        JavaRDD<T>[] array = rdds.toArray(new JavaRDD[rdds.size()]);
        JavaRDD<T> finRDD = ctx.union(array);
        // System.out.println("Total records: " + finRDD.collect().size());

        DataFrame dataFrame = sqlContext.createDataFrame(finRDD, clazz);
        dataFrame.saveAsParquetFile(to);
    }
}
