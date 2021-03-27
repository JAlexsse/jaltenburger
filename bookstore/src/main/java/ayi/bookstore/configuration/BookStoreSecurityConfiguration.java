package ayi.bookstore.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

@Configuration
public class BookStoreSecurityConfiguration extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/home", "/about").permitAll()
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers("/user/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                .and()
                .exceptionHandling()
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandlerImpl();
                        accessDeniedHandler.handle(request, response, accessDeniedException);
                    });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();        
        
        auth.inMemoryAuthentication()
            .passwordEncoder(encoder)
                .withUser("user")
                .password(encoder.encode("password"))            
                .roles("USER")
            .and()
            .passwordEncoder(encoder)
                .withUser("admin")
                .password(encoder.encode("password"))            
                .roles("ADMIN");
    }

}