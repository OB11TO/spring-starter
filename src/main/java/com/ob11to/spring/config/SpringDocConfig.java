package com.ob11to.spring.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("All practical information on Spring")
                        .description("Much remains to be done!")
                        .version("v0.0.1")
                        .license(new License().name("OB11TO Rep").url("https://github.com/OB11TO/spring-starter")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringDoc Documentation")
                        .url("https://springdoc.org/#migrating-from-springfox"));
    }

    @Bean
    public GroupedOpenApi usersGroup() {
        return GroupedOpenApi.builder().group("full")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
                    return operation;
                })
                .packagesToScan("com.ob11to.spring")
                .build();
    }

}