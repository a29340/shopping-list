package com.a29340.shoppinglist.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

  @Value("${enableCORS}")
  Boolean enableCORS;

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {

      @Override
      public void addCorsMappings(CorsRegistry registry) {
        if(enableCORS) {
          registry.addMapping("/api/shoppinglist").allowedOrigins("http://localhost:4200");
        }
      }
    };
  }
}
