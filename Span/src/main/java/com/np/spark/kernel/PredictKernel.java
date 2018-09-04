package com.np.spark.kernel;

import com.np.metastore.data.Analysis;
import com.np.metastore.data.Dataset;
import com.np.metastore.kernel.AnalysisKernel;
import com.np.metastore.kernel.Core;
import com.np.metastore.kernel.DatasetKernel;
import com.np.metastore.management.SystemException;
import com.np.spark.data.LinearRegressionResponse;
import com.np.spark.data.PredictRequest;
import com.np.util.Config;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import parquet.org.codehaus.jackson.JsonNode;
import parquet.org.codehaus.jackson.map.ObjectMapper;
import parquet.org.codehaus.jackson.map.ObjectReader;
import parquet.org.codehaus.jackson.map.ObjectWriter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class PredictKernel implements Serializable {

    /**
     * Load saved model from existing analysis
     * @param analysisId
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     * @throws ClassNotFoundException
     */
    protected Object loadModelFromAnalysis(String analysisId) throws IOException, SQLException, ClassNotFoundException {
        Analysis analysisWithModel = AnalysisKernel.get(analysisId);

        if (analysisWithModel==null || analysisWithModel.getType()==null)
            throw new SystemException("Cannot get base analysis");

        if (!analysisWithModel.getType().equals(getBaseAnalysisType()))
            throw new SystemException("Incorrect base analysis type");

        final ObjectReader or = new ObjectMapper().reader(LinearRegressionResponse.class);
        JsonNode tree = or.readTree(analysisWithModel.getOutput());
        byte[] serializedModel = tree.get("model").getBinaryValue();

        ByteArrayInputStream stream = new ByteArrayInputStream(serializedModel);
        ObjectInputStream ois = new ObjectInputStream(stream);
        final Object model = ois.readObject();
        ois.close();
        stream.close();

        return model;
    }

    /**
     * Generates row based on required schema, prediction and features. This method
     * converts field types if required.
     * @param newSchema
     * @param prediction
     * @param features
     * @return
     */
    protected Row getRowWithPrediction(StructType newSchema, double prediction, double[] features) {
        StructField[] fields = newSchema.fields();

        // build array with data and predictions
        double[] res = new double[fields.length];
        res[0] = prediction;
        for (int i=1;i<res.length;i++)
            res[i] = features[i-1];

        // now build array of Objects of specified types
        Object[] fin = new Object[fields.length];
        for (int j=0;j<res.length;j++) {
            StructField sf = fields[j];
            String typeName = sf.dataType().typeName();
            if (typeName.equals("double")) // TODO: probably need to check other possible types
                fin[j] = new Double(res[j]);
            else if (typeName.equals("integer"))
                fin[j] = new Integer((int)res[j]);
            else if (typeName.equals("long"))
                fin[j] = new Long((long)res[j]);
        }

        return new GenericRowWithSchema(fin, newSchema);
    }

    /**
     * Generates modified schema: all fields will be the same, but result field
     * (which contains prediction) will be added
     * @param schema
     * @return
     */
    protected StructType getSchemaWithResult(StructType schema) {
        // now create newSchema, where type if first field always will be double result
        List<StructField> fields = new ArrayList<StructField>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

        fields.add(DataTypes.createStructField("Result-" + df.format(new Date(System.currentTimeMillis())), DataTypes.DoubleType, true));

        for (StructField field: schema.fields()) {
            DataType type = field.dataType();
            fields.add(DataTypes.createStructField(field.name(), type, true));
        }
        return DataTypes.createStructType(fields);
    }

    public void predict(Analysis an, final PredictRequest lrr) {
        final HiveContext hiveContext = Config.getInstance().getHiveContext();
        boolean fileCreated = false;

        try {
            final DataFrame resultSet = hiveContext.sql(lrr.getQuery());
            final JavaRDD<Row> rowRDD = resultSet.javaRDD();

            // we use this schema as source for field names and types
            final StructType newSchema = getSchemaWithResult(resultSet.schema());

            // Create JavaRDD of rows with predictions
            // This method should load model from specified analysis
            JavaRDD<Row> valuesAndPreds = predictRow(lrr.getModelAnalysisId(), newSchema, rowRDD);

            DataFrame df = hiveContext.createDataFrame(valuesAndPreds, newSchema);
            //deprecated - df.saveAsParquetFile(lrr.getDatasetUrl());
            df.write().parquet(lrr.getDatasetUrl());

            fileCreated = true;

            // create dataset
            Dataset ds = DatasetKernel.create(new Dataset(lrr.getDatasetName(), lrr.getDatasetUrl(), lrr.getDatasetTable(), lrr.getQuery(), "Created by running " + lrr.getName() + " analysis", null, lrr.getUser(),""));

            // serialize
            final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String responseJSON = ow.writeValueAsString("'Created dataset " + ds.toString());
            System.out.println("*** Result: " + responseJSON);

            // Store into analysis
            an.setOutput(responseJSON);
            an.setState("DONE");
            an = AnalysisKernel.finish(an);
        } catch (Throwable ex) {
            ex.printStackTrace();
            an.setOutput(ex.getMessage());
            an.setState("ERROR");
            try {
                an = AnalysisKernel.finish(an);
                if (fileCreated)  // remove created parquet file
                    Core.deleteParquet(lrr.getDatasetUrl());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Load model and use it to predict rows
     * @param analysisId
     * @param newSchema
     * @param rowRDD
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    protected JavaRDD<Row> predictRow(String analysisId, final StructType newSchema, JavaRDD<Row> rowRDD) throws SQLException, IOException, ClassNotFoundException {
        // load the model
        final Object model = loadModelFromAnalysis (analysisId);

        JavaRDD<Row> valuesAndPreds = rowRDD.map(new Function<Row, Row>() {
            @Override
            public Row call(Row row) throws Exception {
                // modelPredict new value
                double[] features = new double[row.size()];
                for (int i=0;i<features.length;i++)
                    features[i] = Util.getAsDoubleExceptionUnhandled(row, i);

                double prediction = modelPredict(model, features);
                return getRowWithPrediction(newSchema, prediction, features);
            }
        });
        return valuesAndPreds;
    }


    /**
     * Run predict for some specified model.
     * @param model
     * @param features
     * @return
     */
    abstract double modelPredict(Object model, double[] features);

    /**
     * Used to check that base analysis uses the required model
     * @return
     */
    abstract String getBaseAnalysisType();
}
