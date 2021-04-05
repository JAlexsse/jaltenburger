package ayi.bookstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ayi.bookstore.model.Adress;

@Configuration
@ComponentScan(basePackageClasses = Adress.class)
public class AdressBeanConfiguration {

    @Bean
    public Adress publishingAdress() {
        return new Adress();        
    }

}