package com.buptse.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Bargain implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bargin_id",type = IdType.AUTO)
    private Integer bargin_id;

    private Integer uid;

    private Integer car_id;

    private BigDecimal price;


    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime start_time;


    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime end_time;

    private Integer is_contact_buyer;

    private Integer is_contact_seller;

}
