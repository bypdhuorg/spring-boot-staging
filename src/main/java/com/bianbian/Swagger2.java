package com.bianbian;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author bianbian
 * @date 2018/12/7
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "test"}) // 第一种在生产环境禁用swagger2方法，指定配置文件
//@ConditionalOnProperty(name = "swagger.enable", havingValue = "true") //第二种禁用swagger2方法，配置文件添加property
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.bianbian.*"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger2 管理页面")
                .description("You can manage here.")
                .termsOfServiceUrl("http://127.0.0.1:8080/swagger-ui.html")
                .contact(new Contact("bianbian","","bian@test.com"))
                .version("1.0")
                .build();
    }
}
