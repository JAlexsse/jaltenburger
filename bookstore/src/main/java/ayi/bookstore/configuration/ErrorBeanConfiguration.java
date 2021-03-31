package ayi.bookstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ayi.bookstore.model.BookStoreException;

@Configuration
@ComponentScan(basePackageClasses = BookStoreException.class)
public class ErrorBeanConfiguration {

    @Bean
    public BookStoreException getBookStoreException(){
        return new BookStoreException();
    }

}
