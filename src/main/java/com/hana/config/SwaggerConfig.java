package com.hana.config;

import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//@OpenAPIDefinition(info = @Info(title = "Project Test", description = "Open API Test",version = "1.0"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

//    @Bean
//    public OpenAPI openAPI(){
//        SecurityScheme securityScheme = new SecurityScheme()
//                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
//                .in(SecurityScheme.In.HEADER).name("Authorization");
//        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");
//
//        return new OpenAPI()
//                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
//                .security(Arrays.asList(securityRequirement));
//    }

    @Bean
    public GroupedOpenApi chatOpenApiConsult() {
        String[] paths = { "/api/consult/**" }; // 해당 path인경우에만 스웨거에 추가되도록 설정

        return GroupedOpenApi
                .builder()
                .group("Consult")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(
                        openApi -> openApi.setInfo(
                                new Info()
                                        .title("API - Consult") // API 제목
                                        .description("Meteor Consult API") // API 설명
                                        .version("1.0.0") // API 버전
                        )
                )
                .build();
    }

    @Bean
    public GroupedOpenApi chatOpenApiContract() {
        String[] paths = { "/api/contract/**" }; // 해당 path인경우에만 스웨거에 추가되도록 설정

        return GroupedOpenApi
                .builder()
                .group("Contract")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(
                        openApi -> openApi.setInfo(
                                new Info()
                                        .title("API - Contract") // API 제목
                                        .description("Meteor Contract API") // API 설명
                                        .version("1.0.0") // API 버전
                        )
                )
                .build();
    }

    @Bean
    public GroupedOpenApi chatOpenApiFund() {
        String[] paths = { "/api/fund/**" }; // 해당 path인경우에만 스웨거에 추가되도록 설정

        return GroupedOpenApi
                .builder()
                .group("Fund")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(
                        openApi -> openApi.setInfo(
                                new Info()
                                        .title("API - Fund") // API 제목
                                        .description("Meteor Fund API") // API 설명
                                        .version("1.0.0") // API 버전
                        )
                )
                .build();
    }

    @Bean
    public GroupedOpenApi chatOpenApiPb() {
        String[] paths = { "/api/pb/**" }; // 해당 path인경우에만 스웨거에 추가되도록 설정

        return GroupedOpenApi
                .builder()
                .group("PB")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(
                        openApi -> openApi.setInfo(
                                new Info()
                                        .title("API - PB") // API 제목
                                        .description("Meteor PB API") // API 설명
                                        .version("1.0.0") // API 버전
                        )
                )
                .build();
    }

    @Bean
    public GroupedOpenApi chatOpenApiPortfolio() {
        String[] paths = { "/api/portfolio/**" }; // 해당 path인경우에만 스웨거에 추가되도록 설정

        return GroupedOpenApi
                .builder()
                .group("Portfolio")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(
                        openApi -> openApi.setInfo(
                                new Info()
                                        .title("API - Portfolio") // API 제목
                                        .description("Meteor Portfolio API") // API 설명
                                        .version("1.0.0") // API 버전
                        )
                )
                .build();
    }

    @Bean
    public GroupedOpenApi chatOpenApiSecurity() {
        String[] paths = { "/api/security/**" }; // 해당 path인경우에만 스웨거에 추가되도록 설정

        return GroupedOpenApi
                .builder()
                .group("Security")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(
                        openApi -> openApi.setInfo(
                                new Info()
                                        .title("API - Security") // API 제목
                                        .description("Meteor Security API") // API 설명
                                        .version("1.0.0") // API 버전
                        )
                )
                .build();
    }

    @Bean
    public GroupedOpenApi chatOpenApiSuggestion() {
        String[] paths = { "/api/suggestion/**" }; // 해당 path인경우에만 스웨거에 추가되도록 설정

        return GroupedOpenApi
                .builder()
                .group("Suggestion")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(
                        openApi -> openApi.setInfo(
                                new Info()
                                        .title("API - Suggestion") // API 제목
                                        .description("Meteor Suggestion API") // API 설명
                                        .version("1.0.0") // API 버전
                        )
                )
                .build();
    }

    @Bean
    public GroupedOpenApi chatOpenApiUser() {
        String[] paths = { "/api/vip/**" }; // 해당 path인경우에만 스웨거에 추가되도록 설정

        return GroupedOpenApi
                .builder()
                .group("Vip")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(
                        openApi -> openApi.setInfo(
                                new Info()
                                        .title("API - Vip") // API 제목
                                        .description("Meteor Vip API") // API 설명
                                        .version("1.0.0") // API 버전
                        )
                )
                .build();
    }
}