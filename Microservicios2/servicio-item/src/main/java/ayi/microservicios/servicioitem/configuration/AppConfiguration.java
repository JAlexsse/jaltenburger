package ayi.microservicios.servicioitem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {
    
    @Bean("clienteRest")
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
