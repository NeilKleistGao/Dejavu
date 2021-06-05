package com.buptse.dto;

import com.buptse.common.RESULT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sound.sampled.Line;

/**
 * @ClassName CommonResult
 * @Description TODO
 * @Author ASJA
 * @Date 2021/6/4 15:48
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private T result;
    private String info;

    private Integer errCode;

    /**
     * 快速返回成果dto
     * @param result
     * @param info
     * @param <T>
     * @return
     */
    public static <T> CommonResult success(T result, String info){
        return new CommonResult<T>(result,info,0);
    }

    /**
     * 快速返回成果dto
     * @param result
     * @param <T>
     * @return
     */
    public static <T> CommonResult success(T result){
        return new CommonResult<T>(result,RESULT.SUCCESS.getMsg(),RESULT.SUCCESS.getErrorCode());
    }

    /**
     * 快速失败返回
     * @param result
     * @param <T>
     * @return
     */
    public static <T> CommonResult failFast(RESULT result){
        return new CommonResult<T>(null,result.getMsg(),result.getErrorCode());
    }

    /**
     * 快速失败返回
     * @param result
     * @param <T>
     * @return
     */
    public static <T> CommonResult failFast(RESULT result,T res){
        return new CommonResult<T>(res,result.getMsg(),result.getErrorCode());
    }


}
