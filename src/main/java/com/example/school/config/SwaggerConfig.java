package com.example.school.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("spring-public")
                .packagesToScan("com.example.school")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.setSummary("Summary of API endpoint");
                    return operation;
                })
                .build();
    }
}
