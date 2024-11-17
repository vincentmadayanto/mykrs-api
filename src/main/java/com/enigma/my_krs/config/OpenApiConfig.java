package com.enigma.my_krs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MyKRS API")
                        .description("API for MyKRS app")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Vincent Madayanto")
                                .email("vincentmadayanto@gmail.com")
                        ));
    }
}
