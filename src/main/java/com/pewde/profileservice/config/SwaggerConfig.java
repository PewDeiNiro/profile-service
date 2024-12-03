package com.pewde.profileservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Profile Service",
                description = "Все операции связанные с профилем",
                version = "1.0.0"
        )
)
public class SwaggerConfig {
}
