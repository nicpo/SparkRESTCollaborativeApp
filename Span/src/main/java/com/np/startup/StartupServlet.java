package com.np.startup;

import com.np.metastore.data.CSVFormat;
import com.np.metastore.kernel.Core;
import com.np.metastore.kernel.DatasetKernel;
import com.np.metastore.kernel.ImportKernel;
import com.np.spark.kernel.SparkKernel;
import com.np.spark.kernel.Util;
import com.np.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Application startup begin there.
 */

public class StartupServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(StartupServlet.class);
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();


    public void init() throws ServletException {

        //String tree = "DecisionTreeModel regressor of depth 5 with 63 nodes\n  If (feature 2 <= 56.0)\n   If (feature 2 <= 12.0)\n    If (feature 4 <= 29.0)\n     If (feature 2 <= 0.0)\n      If (feature 4 <= 16.0)\n       Predict: -7.87465788478227\n      Else (feature 4 > 16.0)\n       Predict: -1.7580706418533991\n     Else (feature 2 > 0.0)\n      If (feature 4 <= 18.0)\n       Predict: 0.6231715102561968\n      Else (feature 4 > 18.0)\n       Predict: 6.960976463382623\n    Else (feature 4 > 29.0)\n     If (feature 4 <= 59.0)\n      If (feature 4 <= 44.0)\n       Predict: 11.328787098987819\n      Else (feature 4 > 44.0)\n       Predict: 25.454273504273505\n     Else (feature 4 > 59.0)\n      If (feature 4 <= 102.0)\n       Predict: 50.396933560477\n      Else (feature 4 > 102.0)\n       Predict: 114.70048309178743\n   Else (feature 2 > 12.0)\n    If (feature 2 <= 30.0)\n     If (feature 4 <= 50.0)\n      If (feature 4 <= 22.0)\n       Predict: 15.103937007874016\n      Else (feature 4 > 22.0)\n       Predict: 28.339503661513426\n     Else (feature 4 > 50.0)\n      If (feature 4 <= 86.0)\n       Predict: 61.0063829787234\n      Else (feature 4 > 86.0)\n       Predict: 119.90579710144928\n    Else (feature 2 > 30.0)\n     If (feature 4 <= 47.0)\n      If (feature 2 <= 41.0)\n       Predict: 33.43591074208614\n      Else (feature 2 > 41.0)\n       Predict: 46.3259807549963\n     Else (feature 4 > 47.0)\n      If (feature 4 <= 92.0)\n       Predict: 80.4622030237581\n      Else (feature 4 > 92.0)\n       Predict: 153.53846153846155\n  Else (feature 2 > 56.0)\n   If (feature 2 <= 138.0)\n    If (feature 2 <= 85.0)\n     If (feature 4 <= 48.0)\n      If (feature 2 <= 69.0)\n       Predict: 61.03900709219858\n      Else (feature 2 > 69.0)\n       Predict: 75.56797966963151\n     Else (feature 4 > 48.0)\n      If (feature 4 <= 98.0)\n       Predict: 113.90508474576271\n      Else (feature 4 > 98.0)\n       Predict: 193.2295081967213\n    Else (feature 2 > 85.0)\n     If (feature 4 <= 42.0)\n      If (feature 2 <= 98.0)\n       Predict: 89.43383199079402\n      Else (feature 2 > 98.0)\n       Predict: 114.05632\n     Else (feature 4 > 42.0)\n      If (feature 4 <= 86.0)\n       Predict: 146.57142857142858\n      Else (feature 4 > 86.0)\n       Predict: 218.77777777777777\n   Else (feature 2 > 138.0)\n    If (feature 1 <= 1734.0)\n     If (feature 3 <= 19.0)\n      If (feature 1 <= 849.0)\n       Predict: 248.52\n      Else (feature 1 > 849.0)\n       Predict: 222.2675544794189\n     Else (feature 3 > 19.0)\n      If (feature 1 <= 1411.0)\n       Predict: 415.05882352941177\n      Else (feature 1 > 1411.0)\n       Predict: 226.42857142857142\n    Else (feature 1 > 1734.0)\n     If (feature 4 <= 29.0)\n      If (feature 3 <= 14.0)\n       Predict: 193.47554925584691\n      Else (feature 3 > 14.0)\n       Predict: 218.95\n     Else (feature 4 > 29.0)\n      If (feature 3 <= 1443.0)\n       Predict: 232.13194444444446\n      Else (feature 3 > 1443.0)\n       Predict: 545.8\n";
        //System.out.println(Util.reFormatDecisionTree(tree));

        try {
            Config.getInstance().load();
            Config.getInstance().initSpark();

            // System.out.println(Core.datasetSize("file:///etc/span/data/fb.parquet"));

            // System.out.println(Core.datasetSize("s3n://csv-data/ft-utf8"));

//            Core.deleteParquet("s3n://csv-data/dennis_test-20150524201626.parquet");

            // test CSV Import
            // System.out.println(ImportKernel.guessFormat(ImportKernel.loadS3("csv-data", "fb-utf8.csv")));
            // System.out.println(ImportKernel.guessFormat(ImportKernel.loadS3("our-environment", "FacebookOutputN.csv")));
            // System.out.println(ImportKernel.guessFormat(ImportKernel.loadS3("our-environment", "VKMediaPosts.csv")));
            // System.out.println(ImportKernel.guessFormat(ImportKernel.loadS3("our-environment/LogisticRegressionTest", "ClassifiedTwitterMessages.csv")));

/*            String fileName = "C:\\TestData\\LogisticRegressionTest\\FacebookCompanies.csv";
            CSVFormat format = ImportKernel.guessFormat(new FileInputStream(fileName));
            String sample = ImportKernel.sample(format, new FileInputStream(fileName));
            ImportKernel.createParquet(format, new FileInputStream(fileName), 5000000, new String[]{"ID","CompanyName","Sales","Employees","MarketCap","Symbol","Location"}, "file:///fbcomp_small.parquet");
*/

            // create sample db
            if (false && Config.isDevelopmentServer()) {
                SampleGenerator sg = new SampleGenerator();
                sg.deleteEverything();
                sg.user1Story();
                sg.user1Analysis();
                sg.showWhatsVisibleForUser2();
            }

            scheduler.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    try {
                        SparkKernel.processQueue();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }, 10, 1, TimeUnit.SECONDS);


        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }

    }

    @Override
    public void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        scheduler.shutdown();

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.info(String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                logger.error(String.format("Error deregistering driver %s", driver), e);
            }

        }

        super.destroy();
    }



}
