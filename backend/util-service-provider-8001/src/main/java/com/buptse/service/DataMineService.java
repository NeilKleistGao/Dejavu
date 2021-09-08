package com.buptse.service;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @ClassName DataMineService
 * @Description TODO
 * @Author ASJA
 * @Date 2021/9/7 15:10
 * @Version 1.0
 */
@Service
public class DataMineService {

    public static final Double V0 = 0.0;
    public static final Double V2 = 0.0;
    public static final Double V3 = 0.0;
    public static final Double V4 = 0.0;
    public static final Double V6 = 0.0;

    public static final StructType schema = new StructType()
            .add("brand", DataTypes.IntegerType, true)
            .add("fuelType", DataTypes.IntegerType, true)
            .add("gearBox", DataTypes.IntegerType, true)
            .add("power", DataTypes.IntegerType, true)
            .add("kilometer", DataTypes.DoubleType, true)
            .add("unrepairedDamage", DataTypes.DoubleType, true)
//            .add("price", DataTypes.DoubleType, true)
            .add("v0", DataTypes.DoubleType, true)
            .add("v2", DataTypes.DoubleType, true)
            .add("v3", DataTypes.DoubleType, true)
            .add("v4", DataTypes.DoubleType, true)
            .add("v6", DataTypes.DoubleType, true);

    public static volatile PipelineModel pipelineModel;
    public static SQLContext sqlContext;
    public static  SQLContext LoadSpark(){
        if(sqlContext==null) {
            SparkConf conf = new SparkConf().setAppName("DataMineService").setMaster("local");
            JavaSparkContext sc = new JavaSparkContext(conf);
            sqlContext = new SQLContext(sc);
        }
        return sqlContext;
    }
    public static  PipelineModel loadPipelineModel(){
        if(pipelineModel==null){
            synchronized (DataMineService.class){
                if(pipelineModel == null) {
                    pipelineModel = PipelineModel.load("./model/rf.model");
                }
            }
        }
        return pipelineModel;
    }

}