package cl.dgac.pilotos.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate; // ¡AGRÉGALA!

@Configuration
public class WebClientConfig { // Mantenemos el nombre de la clase igual para que no tengas que renombrar el archivo

    @Bean
    @LoadBalanced // ¡La magia que conecta con Eureka!
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

