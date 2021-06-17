package com.buptse.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@JsonComponent
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
  @Bean
  public LocalDateTimeSerializer localDateTimeDeserializer() {
    return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
  }
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer());
  }
}
