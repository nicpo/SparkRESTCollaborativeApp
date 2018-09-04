package com.np.metastore.kernel;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CSVLineParser {
    private final ThreadLocal<CsvParser> parser = new ThreadLocal<>();
    private final String[] fieldTypes;
    private final CsvParserSettings settings;
    private final ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    private final List<Callable<Row>> tasks = new ArrayList<>(100000);
    private final AtomicInteger itemsReceived = new AtomicInteger(0);
    private final AtomicInteger itemsProcessed = new AtomicInteger(0);
    private final List<String> failedLines = Collections.synchronizedList(new ArrayList<String>());

    public CSVLineParser(CsvParserSettings settings, String[] fieldTypes)
    {
        this.settings = settings;
        this.fieldTypes = fieldTypes;
    }

    private CsvParser getParser()
    {
        if (parser.get()==null) {
            parser.set(new CsvParser(settings));
        }

        return parser.get();
    }

    public void submitLine(final String lineToParse)
    {
        itemsReceived.incrementAndGet();

        tasks.add(new Callable<Row>() {
            @Override
            public Row call() throws Exception {
                String[] v1 = getParser().parseLine(lineToParse);

                if (v1==null) {
                    return null;
                }

                if (v1.length != fieldTypes.length) {
                    // System.out.println("Skipped line, column count doesn't match " + Arrays.toString(v1));
                    failedLines.add(lineToParse);
                    return null;
                }

                try {
                    Object rec[] = new Object[v1.length];
                    for (int i = 0; i < v1.length; ++i) {
                        if (fieldTypes[i].equals("DOUBLE")) {
                            if (v1[i] != null)
                                rec[i] = Double.valueOf(v1[i]);
                            else
                                rec[i] = null; // new Double(0);
                        } else if (fieldTypes[i].equals("LONG")) {
                            if (v1[i] != null)
                                rec[i] = Long.valueOf(v1[i]);
                            else
                                rec[i] = null; // new Long(0);
                        } else {
                            if (v1[i] != null)
                                rec[i] = v1[i];
                            else
                                rec[i] = null; // "";
                        }
                    }

                    itemsProcessed.incrementAndGet();
                    return RowFactory.create(rec);
                } catch (NumberFormatException e) {
                    // System.out.println(e.getMessage());
                    failedLines.add(lineToParse);
                    return null;
                }
            }
        });
    }

    /**
     * Get specified count of failed items
     * @param count
     * @return
     */
    public List<String> getFailedLines(int count)
    {
        if (count>failedLines.size())
            return failedLines;
        else
            return failedLines.subList(0,count-1);
    }

    public ArrayList<Row> getRows() {

        List<Future<Row>> futures = null;
        try {
            futures = exec.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<Row> ret = new ArrayList<>(tasks.size());
        for (int i=0;i<futures.size();i++)
        {
            Future<Row> item = futures.get(i);
            try {
                Row r = item.get();
                if (r!=null)
                    ret.add(r);
                futures.set(i,null); // free memory immediate
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    public void clearTaskList()
    {
        tasks.clear();
    }

    public void stop()
    {
        exec.shutdown();
    }

    public int getSize()
    {
        return tasks.size();
    }

    public int getProcessedLines()
    {
        return itemsProcessed.get();
    }

    public int getReceivedLines()
    {
        return itemsReceived.get();
    }

}
