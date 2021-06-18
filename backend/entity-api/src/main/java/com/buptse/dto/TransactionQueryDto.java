package com.buptse.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
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

    private Integer buyer;

    private Integer seller;
    
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime date;


}
