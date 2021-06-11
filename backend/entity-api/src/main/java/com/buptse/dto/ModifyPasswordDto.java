package com.buptse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyPasswordDto {
  Integer uid;
  String oldPassword;
  String newPassword;
  String token;
}
