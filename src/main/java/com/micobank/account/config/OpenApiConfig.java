package com.micobank.account.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI/Swagger 3.0 Configuration
 * Provides comprehensive API documentation and metadata
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .servers(List.of(
                        devServer(),
                        prodServer()
                ));
    }

    private Info apiInfo() {
        return new Info()
                .title("MicoBank Account Service API")
                .description("RESTful API for managing customer accounts in the MicoBank microservices ecosystem. " +
                        "This service handles account creation, updates, retrieval, and deletion operations.")
                .version("1.0.0")
                .contact(contactInfo())
                .license(licenseInfo());
    }

    private Contact contactInfo() {
        return new Contact()
                .name("MicoBank Development Team")
                .url("https://micobank.com")
                .email("api-support@micobank.com");
    }

    private License licenseInfo() {
        return new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0.html");
    }

    private Server devServer() {
        return new Server()
                .url("http://localhost:8080")
                .description("Development Server");
    }

    private Server prodServer() {
        return new Server()
                .url("https://api.micobank.com")
                .description("Production Server");
    }
}
