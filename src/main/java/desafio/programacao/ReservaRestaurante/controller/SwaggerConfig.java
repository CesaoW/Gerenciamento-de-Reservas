package desafio.programacao.ReservaRestaurante.controller;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private static final String BEARER_KEY_SECURITY_SCHEME = "bearer-key";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gerenciamento de Reservas de Restaurante")
                        .description("Sistema completo para gerenciamento de reservas, mesas e usuários de um restaurante. " +
                                "Esta API oferece funcionalidades para criar, listar, cancelar e deletar reservas, " +
                                "além de gerenciar mesas e controlar acesso através de autenticação JWT.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("César Nogueira")
                                .url("https://github.com/CesaoW")
                                .email("cesarnogueirarodrigues1200@gmail.com")))
                //.addSecurityItem(new SecurityRequirement().addList(BEARER_KEY_SECURITY_SCHEME))
                .components(new Components()
                        .addSecuritySchemes(BEARER_KEY_SECURITY_SCHEME,
                                new SecurityScheme()
                                        .name(BEARER_KEY_SECURITY_SCHEME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Insira o token JWT obtido através do endpoint /user/login. " +
                                                "Formato: Bearer <seu-jwt-token>")
                        )
                );
    }
}