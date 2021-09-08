import org.apache.spark.api.java.*;
import org.apache.spark.SparkConf;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.ml.regression.RandomForestRegressor;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

import java.io.File;

public class RandomForest {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("RandomForest").setMaster("local");
        try (JavaSparkContext sc = new JavaSparkContext(conf)) {
            JavaRDD<String> rdd = sc.textFile("/home/neilkleistgao/Documents/Code/Java/Dejavu/dm/dataset/final.csv");
            var data = rdd.map((String line) -> {
                String[] arr = line.split(",");
                return new Object[] {Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3]),
                        Double.parseDouble(arr[4]), Double.parseDouble(arr[5]), Double.parseDouble(arr[6]), Double.parseDouble(arr[7]),
                        Double.parseDouble(arr[8]), Double.parseDouble(arr[9]), Double.parseDouble(arr[10]), Double.parseDouble(arr[11])};
            }).map((RowFactory::create));

            var sql = new SQLContext(sc);
            var schema = new StructType()
                    .add("brand", DataTypes.IntegerType, true)
                    .add("fuelType", DataTypes.IntegerType, true)
                    .add("gearBox", DataTypes.IntegerType, true)
                    .add("power", DataTypes.IntegerType, true)
                    .add("kilometer", DataTypes.DoubleType, true)
                    .add("unrepairedDamage", DataTypes.DoubleType, true)
                    .add("price", DataTypes.DoubleType, true)
                    .add("v0", DataTypes.DoubleType, true)
                    .add("v2", DataTypes.DoubleType, true)
                    .add("v3", DataTypes.DoubleType, true)
                    .add("v4", DataTypes.DoubleType, true)
                    .add("v6", DataTypes.DoubleType, true);
            var dataframe = sql.createDataFrame(data, schema);

            System.out.println("total: " + dataframe.count());
            dataframe.printSchema();

            var dataset = dataframe.randomSplit(new double[]{0.8, 0.2});

            var assembler = new VectorAssembler()
                    .setInputCols(new String[]{"brand", "fuelType", "gearBox", "power", "kilometer", "unrepairedDamage",
                            "v0", "v2", "v3", "v4", "v6"})
                    .setOutputCol("features");

            var model = new RandomForestRegressor()
                    .setLabelCol("price")
                    .setFeaturesCol("features")
                    .setMaxBins(96)
                    .setMaxDepth(30);

            PipelineModel res = null;

            if (new File("./rf.model").exists()) {
                res = PipelineModel.load("./rf.model");
            }
            else {
                var pipeline = new Pipeline()
                        .setStages(new PipelineStage[]{assembler, model});
                res = pipeline.fit(dataset[0]);
                res.write().overwrite().save("./rf.model");
            }

            var predictions = res.transform(dataset[1]);
            predictions.select("prediction").show(3);

            var evaluator = new RegressionEvaluator()
                    .setLabelCol("price")
                    .setPredictionCol("prediction")
                    .setMetricName("mae");

            double mae = evaluator.evaluate(predictions);
            System.out.println("mae: " + mae);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
