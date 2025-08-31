//package com.alpha.pos.config;
//
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import io.swagger.v3.oas.models.security.SecurityScheme.Type;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@OpenAPIDefinition(info = @Info(title = "ALPHA POS System"))
//public class SwaggerConfiguration {
//
//  private SecurityScheme createSwaggerSecurityScheme() {
//    return new SecurityScheme().type(Type.HTTP).bearerFormat("JWT").scheme("bearer");
//  }
//
//  @Bean
//  public OpenAPI openAPI() {
//    return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
//        .components(
//            new Components().addSecuritySchemes("Bearer Authentication", createSwaggerSecurityScheme()));
//  }
//}
