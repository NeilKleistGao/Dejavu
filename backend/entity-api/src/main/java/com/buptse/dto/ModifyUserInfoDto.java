package com.buptse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyUserInfoDto {
  String token;
  Integer userId;
  String mail;
  String avatar;
}
