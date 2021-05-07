package com.buptse.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

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
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "car_id")
    private Integer carId;

    private String modelName;

    private BigDecimal guidePrice;

    private String manufacturer;

    private BigDecimal serviceLife;

    private BigDecimal mileage;

    private BigDecimal displacement;

    private Integer regionCode;

    private Integer uid;

    private BigDecimal price;

    private Integer state;

    private Integer bodyType;

    private Integer fuelType;

    private Integer gearBox;

    private BigDecimal power;

    private Integer notRepairedDamage;

    private LocalDateTime createDate;


}
