package com.buptse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName Carimg
 * @Description TODO
 * @Author ASJA
 * @Date 2021/6/11 16:24
 * @Version 1.0
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Carimg implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer car_id;
    private String img;

}
