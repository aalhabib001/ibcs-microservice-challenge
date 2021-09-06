package bd.info.habib.ibcscodechallenge2.employeeservice.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

//Configuration for Swagger Documentation
@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    private List<Parameter> operationParameters() {
        List<Parameter> headers = new ArrayList<>();
        Parameter authHeader = new ParameterBuilder()
                .parameterType("header")
                .name("Authorization")
                .modelRef(new ModelRef("string"))
                .build();

        headers.add(authHeader);
        return headers;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(metaData())
                .globalOperationParameters(operationParameters());
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Employee-Service",
                "Spring Boot REST API for Employee-Service Project",
                "B 0.1",
                "Terms of service",
                "Abdullah AL Habib",
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}