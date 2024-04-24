package com.apispotify.API.Spotify.Config;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.PathItem;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Clone do Spotify")
                        .version("v1")
                        .description("API criada para simular o funcionamento do Spotify. ")
                        .contact(new Contact()
                                .name("Théo Furtado")
                                .url("https://github.com/theofurtado05/API-Spotify")
                                .email("theofurtado05@gmail.com"))
                );
    }
}