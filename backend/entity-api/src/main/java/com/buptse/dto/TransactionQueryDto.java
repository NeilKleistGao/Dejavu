package com.buptse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName TransactionQueryDto
 * @Description TODO
 * @Author ASJA
 * @Date 2021/6/11 15:42
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionQueryDto {
    private Integer uid;

    private Integer buyer;

    private Integer seller;

}
