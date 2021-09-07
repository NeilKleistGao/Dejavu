package com.buptse.controller;

import com.buptse.dto.CommonResult;
import com.buptse.dto.PredictDto;
import com.buptse.service.DataMineService;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName PredictController
 * @Description TODO
 * @Author ASJA
 * @Date 2021/9/7 9:43
 * @Version 1.0
 */
@RestController
@RequestMapping("//dataming")
public class PredictController {

    @Autowired
    private DataMineService dataMineService;
    
    @PostMapping("/predict")
    public CommonResult predict(
            @RequestParam("brand") Integer brand,
            @RequestParam("fuelType") Integer fuelType,
            @RequestParam("gearBox") Integer gearBox,
            @RequestParam("kilometer") Integer kilometer,
            @RequestParam("unrepairedDamage") Integer unrepairedDamage
    ) {

        List<Row> data = Collections.singletonList(RowFactory.create(brand, fuelType, gearBox, kilometer, unrepairedDamage,
                DataMineService.V0, DataMineService.V2,
                DataMineService.V3, DataMineService.V4, DataMineService.V6));

        PipelineModel model = DataMineService.pipelineModel == null ?
                DataMineService.loadPipelineModel() : DataMineService.pipelineModel;
        SparkConf conf = new SparkConf().setAppName("RandomForest").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sql = new SQLContext(sc);
        Dataset<Row> dataFrame = sql.createDataFrame(data, DataMineService.schema);
        Dataset<Row> prediction = model.transform(dataFrame);
        Double  price= (Double) prediction.select("prediction").collect();
        return CommonResult.success(PredictDto.genPredictDto(price));

    }
}