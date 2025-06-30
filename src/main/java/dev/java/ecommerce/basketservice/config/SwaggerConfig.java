package dev.java.ecommerce.basketservice.config;


import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Jhonatta-dev")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public io.swagger.v3.oas.models.OpenAPI customOpenApi(){
        return  new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info()
                        .title("API de cadastro de produtos em um carrilho")
                        .version("0.1")
                        .description("Api utiliza uma api externa para os produtos")
                        .contact(new Contact()
                                .name("Jhonatta Costa")
                                .email("Jhonattasantos27@gmail.com")));
    }
}
