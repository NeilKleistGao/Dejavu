package com.buptse.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


public enum RESULT {

    SUCCESS(0,"success"),
    USER_NOT_LOGIN(-1,"user not login"),
    NO_DATA(-2,"no data"),
    NO_PERMISSION(-3,"refuce aceess permission");
    private Integer errorCode;
    private String msg;

    RESULT(Integer errorCode, String msg){
        this.errorCode = errorCode;
        this.msg = msg;
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



}
