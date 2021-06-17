package com.buptse.dto;

import com.buptse.pojo.Transaction;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName TranscationModifyDto
 * @Description TODO
 * @Author ASJA
 * @Date 2021/6/11 15:51
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModifyDto {
    private Integer uid;
    private Integer buyer_id;
    private Integer seller_id;
    private Integer car_id;
    private BigDecimal deal_price;
    private Integer sale_id;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime deal_time;
}
