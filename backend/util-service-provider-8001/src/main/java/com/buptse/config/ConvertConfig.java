package com.buptse.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ConvertConfig {

  @Bean
  public Converter<String, LocalDateTime> localDateTimeConverter(){
    return new Converter<String, LocalDateTime>() {
      @Override
      public LocalDateTime convert(String s) {
        if(StringUtils.isBlank(s)){
          return null;
        }
        return LocalDateTime.parse(s,DateTimeFormatter.ofPattern("yyyy/MM/dd"));
      }
    };
  }
}
