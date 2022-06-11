package ru.efimov.projects.db.dbstand.configuration;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

public class SpringFoxConfiguration {


        private ApiInfo apiInfo() {
            return new ApiInfo(
                    "Simple stand for DB course",
                    "",
                    "0.1",
                    "pass",
                    new Contact("Artem Efimov", "https://t.me/ArtemEfim0v", "artem.efimov@internet.ru"),
                    "pass",
                    "pass",
                    Collections.emptyList());
        }

        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build();
        }
}
