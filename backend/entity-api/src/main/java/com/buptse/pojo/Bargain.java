package com.buptse.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

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

    private Integer uid;

    private Integer carId;

    private BigDecimal price;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


}
