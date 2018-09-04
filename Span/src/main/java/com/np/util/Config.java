package com.np.util;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.np.spark.kernel.Util;
import net.jcip.annotations.ThreadSafe;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.hive.HiveContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

/**
 * Implement config file loading and params parsing and processing
 */

@ThreadSafe
public class Config {
    private static final Config ourInstance = new Config();
    private static final CompositeConfiguration prop = new CompositeConfiguration();
    private JavaSparkContext ctx = null;
    private HiveContext hiveContext = null;
    private Connection conn;


    public Config() {

    }

    public static Config getInstance() {
        return ourInstance;
    }


    public AmazonS3 getS3Client()
    {
        String accessKey = prop.getString("s3.accessKey");
        String secretKey = prop.getString("s3.secretKey");
        AmazonS3 s3 = new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey));
        return s3;
    }

    public Connection getConn() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection("jdbc:sqlite:/etc/span/metastore.db");
            conn.setAutoCommit(false);
        }
        return conn;
    }

    /**
     * loads the main configuration file
     */
    public synchronized void load() throws ConfigurationException, SQLException, ClassNotFoundException, ParserConfigurationException, IOException, SAXException {
/*        if (new File("/etc/span/log4j.properties").exists())
            PropertyConfigurator.configure("/etc/span/log4j.properties");
        else
            BasicConfigurator.configure();
*/
        prop.addConfiguration(new SystemConfiguration());

        Logger logger = LoggerFactory.getLogger(Config.class);
        logger.info("Loading /etc/span/span.properties");
        PropertiesConfiguration p = new PropertiesConfiguration();
        p.setPath("/etc/span/span.properties");
        p.setReloadingStrategy(new FileChangedReloadingStrategy());
        p.load();
        prop.addConfiguration(p);

        // load SQLite driver
        Class.forName("org.sqlite.JDBC");

    }

    public synchronized void initSpark() throws SQLException
    {
        final SparkConf sparkConf = new SparkConf().setAppName("Span");
        File[] jars = new File("/etc/span/jars").listFiles();
        ArrayList<String> fullPaths = new ArrayList<String>();
        if (jars!=null) {
            for (File jar : jars)
                fullPaths.add(jar.getAbsolutePath());
            String[] list = new String[]{};
            sparkConf.setJars(fullPaths.toArray(list));
        }

        sparkConf.setMaster("local[*]");
        Iterator<String> it = prop.getKeys("spark");
        while (it.hasNext()) {
            String key = it.next();
            String value = prop.getString(key);
            sparkConf.set(key,value);

        }
        // sparkConf.registerKryoClasses(new Class[]{CSVFormat.class, CSVImportRequest.class});

        ctx = new JavaSparkContext(sparkConf);
        hiveContext = new HiveContext(ctx.sc());

        // register parquet files
        Util.registerAllDatasets();

        System.out.println("Spark init: done");
    }

    public JavaSparkContext getSparkContext()
    {
        return ctx;
    }

    public HiveContext getHiveContext()
    {
        return hiveContext;
    }

    public static String getShortUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static boolean isDevelopmentServer()
    {
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            if (hostName!=null && hostName.equals("iMac-2.local"))
                return true;
        } catch (Throwable t) {
        }
        return false;
    }

}
