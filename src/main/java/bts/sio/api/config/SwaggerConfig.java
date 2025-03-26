package bts.sio.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Paris 2024",
                description = "API pour gérer les joueurs, les sports, les athlètes, les actualités, etc..",
                version = "v1.0"
        )
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("v1")
                .packagesToScan("bts.sio.api.controller")
                .build();
    }
}
