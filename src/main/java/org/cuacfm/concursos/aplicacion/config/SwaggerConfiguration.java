package org.cuacfm.concursos.aplicacion.config;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger1.annotations.EnableSwagger;

import com.google.common.base.Predicate;

@Configuration
@EnableSwagger
public class SwaggerConfiguration implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_12).apiInfo(apiInfo()).select().paths(paths()).build();
    }

    @Override
    public void setEnvironment(Environment env) {
        propertyResolver = new RelaxedPropertyResolver(env, "swagger.");

    }

    private Predicate<String> paths() {
        return input -> input.startsWith("/api");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(propertyResolver.getProperty("title"))
                .description(propertyResolver.getProperty("description"))
                .termsOfServiceUrl(propertyResolver.getProperty("termsOfServiceUrl"))
                .contact(propertyResolver.getProperty("contact")).license(propertyResolver.getProperty("license"))
                .licenseUrl(propertyResolver.getProperty("licenseUrl")).build();
    }

}
