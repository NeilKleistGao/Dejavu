package com.buptse.dto;

import com.buptse.pojo.Transaction;
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
public class TranscationModifyDto extends Transaction {

    private Integer uid;
}
