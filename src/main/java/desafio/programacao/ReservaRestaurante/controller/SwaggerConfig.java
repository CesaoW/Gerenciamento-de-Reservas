package desafio.programacao.ReservaRestaurante.controller;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customAPI(){
        return new OpenAPI().info(new Info()
                .title("Reservas de Restaurante")
                .version("1.0.0")
                .description("Sistema de gerenciamento de reservas de um restaurante")
        );
    }
}
