package uz.alif.server.config;//package uz.zako.lesson22.config;// Author - Orifjon Yunusjonov
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.VendorExtension;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static springfox.documentation.builders.PathSelectors.regex;
//
//// t.me/coderr24
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig extends WebMvcConfigurationSupport {
//
//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("uz.zako.lesson22.controller"))
//                .paths(regex("/.*"))
//                .build()
//                .apiInfo(metaData());
//    }
//    private ApiInfo metaData() {
//        List<VendorExtension> vendorExtensions = new ArrayList<>();
//        ApiInfo apiInfo = new ApiInfoBuilder().title("Example api").description("Spring Boot REST API for Online Test")
//                .license("Apache License Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
//                .contact(new Contact("Yunusjonov Orifjon", "http://localhost:8084/", "mryediniofficial9924@gmail.com")).build();
//        return apiInfo;
//    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//}
