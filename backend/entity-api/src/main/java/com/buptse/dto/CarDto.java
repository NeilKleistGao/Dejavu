package com.buptse.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
  private Integer car_id;

  private String model_name;

  private BigDecimal guide_price;

  private String manufacturer;

  private BigDecimal service_life;

  private BigDecimal mileage;

  private BigDecimal displacement;

  private String region;

  private Integer uid;

  private BigDecimal price;

  private Integer state;

  private String body_type;

  private String fuel_type;

  private String gear_box;

  private BigDecimal power;

  private String not_repaired_damage;

  private LocalDateTime create_date;

  private List<String> imgs;

}
