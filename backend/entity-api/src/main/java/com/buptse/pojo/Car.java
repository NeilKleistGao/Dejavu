package com.buptse.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

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
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "car_id",type = IdType.INPUT)
    private Integer car_id;

    private String model_name;

    private BigDecimal guide_price;

    private String manufacturer;

    private BigDecimal service_life;

    private BigDecimal mileage;

    private BigDecimal displacement;

    private Integer region_code;

    private Integer uid;

    private BigDecimal price;

    private Integer state;

    private Integer body_type;

    private Integer fuel_type;

    private Integer gear_box;

    private BigDecimal power;

    private Integer not_repaired_damage;

    private LocalDateTime create_date;


}
