package com.buptse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName PredictDto
 * @Description TODO
 * @Author ASJA
 * @Date 2021/9/7 9:44
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredictDto {

    public static final Integer MAE = 728;

    private Double priceUp;

    private Double priceDown;

    public static PredictDto genPredictDto(Double prince){
        PredictDto predictDto = new PredictDto();
        predictDto.setPriceDown(prince-MAE);
        predictDto.setPriceUp(prince+ MAE);
        return predictDto;
    }

}
