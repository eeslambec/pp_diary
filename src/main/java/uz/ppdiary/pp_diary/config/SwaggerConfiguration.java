package uz.ppdiary.pp_diary.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI(){
        final String securitySchemeName = "Bearer Authentication";
        return new OpenAPI().info(new Info()
                .title("")
                .description("")
                .version("1.0.0")
                .contact(new Contact()
                        .name("Ismoil")
                        .email("abduganiyev.ismoil001@gmail.com")
                        .url("https://t.me/ismoil_0709")
                )
        ).addSecurityItem(new SecurityRequirement()
                .addList(securitySchemeName)
        ).components(new Components()
                .addSecuritySchemes(securitySchemeName,new SecurityScheme()
                        .name(securitySchemeName)
                        .bearerFormat("JWT")
                        .scheme("bearer")
                        .type(SecurityScheme.Type.HTTP)
                )
        );
    }
}
