package main.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class AppConfig {
	
	//Para HTTP Requests
	
    @Bean("RestClient")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    //Para documentacion
    
    @Bean("OpenAPI")
    public OpenAPI customOpenAPI(@Value("${application-description}") String description,
                                 @Value("${application-version}") String version) {
        return new OpenAPI()
                .components(new Components()
                .addSecuritySchemes("bearerAuth", 
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")))
                .info(new Info().title("Travel API")
                    .version(version)
                    .description(description)
                    .license(new License().name("Travel API Licence")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}