package com.ob11to.spring.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info().title("All practical information on Spring")
                        .description("Much remains to be done!")
                        .version("v0.0.1")
                        .license(new License().name("OB11TO Rep").url("https://github.com/OB11TO/spring-starter")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringDoc Documentation")
                        .url("https://springdoc.org/#migrating-from-springfox"))
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                ;
    }

    @Bean
    public GroupedOpenApi emailGroup(@Value("${springdoc.version}") String appVersion) {
        return GroupedOpenApi.builder().group("Email API")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
                    return operation;
                })
                .addOpenApiCustomiser(openApi -> openApi.info(new Info().title("Email API").version(appVersion)))
                .pathsToMatch("/email/**")
                .build();
    }

    @Bean
    public GroupedOpenApi reportGroup(@Value("${springdoc.version}") String appVersion) {
        return GroupedOpenApi.builder().group("Reports API")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.addSecurityItem(new SecurityRequirement().addList("basicScheme"));
                    return operation;
                })
                .addOpenApiCustomiser(openApi ->
                        openApi.info(new Info().title("Reports API").version(appVersion))
                                .externalDocs(new ExternalDocumentation()
                                        .description("Report ")
                                        .url("https://github.com/springdoc/springdoc-openapi-demos/blob/master/springdoc-openapi-spring-boot-2-webmvc/src/main/java/org/springdoc/demo/app2/Application.java")))
                .pathsToMatch("/reports")
                .build();
    }


}