package hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2	//Swagger 2 is enabled through the @EnableSwagger2 annotation
public class SwaggerConfig {
	/**
	 * Bean Docket sẽ xác định các class trong package nào được tạo document,
	 * thông tin là gì… (ví dụ ở đây mình tạo document cho các class trong 
	 * package demo.controller)
	 * 
	 * The configuration of Swagger mainly centers around the Docket bean
	 * 
	 * After the Docket bean is defined, its select() method returns an instance of 
	 * ApiSelectorBuilder, which provides a way to control the endpoints exposed 
	 * by Swagger.
	 * 
	 * Predicates for selection of RequestHandlers can be configured with 
	 * the help of RequestHandlerSelectors and PathSelectors. Using any() for both 
	 * will make documentation for your entire API available through Swagger.
	 * 
	 * This configuration is enough to integrate Swagger 2 into existing Spring Boot 
	 * project. For other Spring projects, some additional tuning is required
	 * 
	 * @return
	 */
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("hello.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    
	/**
	 * @return thông tin hiển thị trên swagger
	 */
    private ApiInfo getApiInfo() {
        Contact contact = new Contact("stackjava.com", "https://stackjava.com", "cuong.9312@gmail.com");
        return new ApiInfoBuilder()		//
                .title("Spring Boot Swagger")	//
                .description("Demo Spring Boot Swagger")	//
                .version("1.0.0")	//
                .license("Apache 2.0")	//
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")	//
                .contact(contact)	//
                .build();
    }
}
