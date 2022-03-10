package org.kenne.noudybaapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openApiConfig() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        Info info = new Info();
        info.title("NOUDYBA API")
                .description("API FOR NOUDYBA ASSOCIATION MANAGEMENT PROCESS")
                .version("V1.0.0")
                .contact(new Contact()
                        .email("kennegervais@gmail.com")
                        .name("Kenne Gervais")
                        .url("http://lolhost:8050/"));
        return info;
    }
}
