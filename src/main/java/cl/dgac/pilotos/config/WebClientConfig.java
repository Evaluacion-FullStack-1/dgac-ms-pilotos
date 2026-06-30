package cl.dgac.pilotos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // Lee la URL desde el application.properties para la conexión interna
    @Value("${usuarios.base-url:http://dgac-ms-usuarios}")
    private String usuariosBaseUrl;

    @Bean
    @LoadBalanced // Fundamental para que Eureka intercepte y resuelva la IP dinámica
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient webClientUsuarios(WebClient.Builder builder) {
        // Construye el cliente apuntando al microservicio de Usuarios
        return builder.baseUrl(usuariosBaseUrl).build();
    }
}