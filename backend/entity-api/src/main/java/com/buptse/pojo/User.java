package com.buptse.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User {
    private int uid;
    private String name;
    private String phone_number;
    private String mail;
    private  String avatar;
    private  String password;
}
