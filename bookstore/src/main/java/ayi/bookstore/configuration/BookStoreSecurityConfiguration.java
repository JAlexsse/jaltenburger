package ayi.bookstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import ayi.bookstore.security.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BookStoreSecurityConfiguration extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/home", "/about").permitAll()
                    //.antMatchers("/admin/**").hasAnyRole(BookstoreUserRoll.ADMIN.name())
                    //.antMatchers("/user/**").hasAnyRole(BookstoreUserRoll.USER.name())
                .anyRequest().authenticated()
                .and()
                .httpBasic()
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
    @Bean
    protected UserDetailsService userDetailsService(){

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();        

        UserDetails admin = User.builder()
            .username("admin")
            .password(encoder.encode("password"))
            .authorities(BookstoreUserRoll.ADMIN.getGrantedAuthorities())
            .build();

        UserDetails user = User.builder()
            .username("user")
            .password(encoder.encode("password"))
            .authorities(BookstoreUserRoll.USER.getGrantedAuthorities())
            .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
    
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();        
        
        auth.inMemoryAuthentication()
            .passwordEncoder(encoder)
                .withUser("user")
                .password(encoder.encode("password"))            
                //.roles(BookstoreUserRoll.USER.name())
                .authorities(BookstoreUserRoll.USER.getGrantedAuthorities())
            .and()
            .passwordEncoder(encoder)
                .withUser("admin")
                .password(encoder.encode("password"))            
                .roles(BookstoreUserRoll.ADMIN.name());
    }
    */

}