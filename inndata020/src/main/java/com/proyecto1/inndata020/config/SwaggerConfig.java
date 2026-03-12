package com.proyecto1.inndata020.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de OpenAPI/Swagger para el proyecto inndata020
 * Usando Springdoc OpenAPI (compatible con Spring Boot 3.3.0 y Jakarta)
 *
 * Acceso: http://localhost:8080/swagger-ui.html
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inndata020 - API REST Documentation")
                        .version("1.0.0")
                        .description("Documentación automática de la API REST del proyecto Inndata020. " +
                                "Aquí encontrarás todos los endpoints disponibles con sus parámetros y respuestas.")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo Inndata")
                                .url("http://example.com")
                                .email("desarrollo@inndata.com")));
    }
}



