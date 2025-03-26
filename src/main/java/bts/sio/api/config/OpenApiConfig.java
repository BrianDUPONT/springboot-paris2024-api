package bts.sio.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("http://172.20.177.251:8080/brianparis2024").description("Serveur interne (Lycée)"),
                        new Server().url("https://prodtomcat.inforostand14.net/brianparis2024").description("Serveur externe (Public)"),
                        new Server().url("http://localhost:9005").description("Local")
                        ));
    }
}