package com.board.board_java.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(@Value("${springdoc.swagger-ui.version}") String version) {
        Info info = new Info()
                .title("board_java") // API Title
                .version(version) // Version
                .description(""); // API 설명

        // SecuritySecheme명
        String jwtSchemeName = "jwtAuth";

        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("bearer")
                        .bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components)
                //.servers(List.of(new Server().url("http://localhost:8080"), new Server().url("https://www.wonjun.site"))); //Server Url 리스트로 추가하는 방법 
                .addServersItem(new Server().url("/")); // Url을 / 로 설정 시 해당 접속 Url로 요청되는것으로 확인됨
    }
}
