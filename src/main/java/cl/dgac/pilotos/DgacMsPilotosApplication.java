package cl.dgac.pilotos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // <--- Esta es la única anotación que llevan los trabajadores
public class DgacMsPilotosApplication {

    public static void main(String[] args) {
        SpringApplication.run(DgacMsPilotosApplication.class, args);
    }
}