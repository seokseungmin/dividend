package zerobase.dividend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi dividendApi() {
        return GroupedOpenApi.builder()
                .group("dividend-api")
                .pathsToMatch("/auth/**", "/company/**", "/finance/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("실전 배당금 프로젝트")
                        .description("크롤링을 통해 주식 데이터를 가져와 배당금을 알아보는 프로젝트입니다.")
                        .version("1.0"));
    }
}
