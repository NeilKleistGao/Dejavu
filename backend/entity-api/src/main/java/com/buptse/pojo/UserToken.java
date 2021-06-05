package com.buptse.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserToken implements Serializable {
  private Integer userId; // 用户ID
  private String token; //用户Token
  private Long expireTime;
  public String getFullToken(){
    return token+"&"+userId;
  }
}
