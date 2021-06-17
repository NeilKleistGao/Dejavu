package com.buptse.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ASJA
 * @since 2021-04-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer buyer_id;

    private Integer seller_id;

    private Integer car_id;

    private BigDecimal deal_price;

    private LocalDateTime deal_time;

    @TableId(value = "sale_id",type = IdType.INPUT)
    private Integer sale_id;


}
