package ayi.bookstore.configuration;

import java.util.concurrent.TimeUnit;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ayi.bookstore.security.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BookStoreSecurityConfiguration extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        /*
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) 
            Es recomendable utilizar csrf cuando usuarios van a poder procesar por browser informacion.
            En estos casos el withHttpOnlyFalse no permite 
            que la cookie se accesible para el cliente, pero hay
            que implementar logica para pasar el token por cada request.
        */

        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/home", "/about").permitAll()
                    .antMatchers("/h2-console/**").hasRole("ADMIN")
                    //.antMatchers("/admin/**").hasAnyRole(BookstoreUserRoll.ADMIN.name())
                    //.antMatchers("/user/**").hasAnyRole(BookstoreUserRoll.USER.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home", true)
                    .permitAll()
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(3))
                .and()
                .logout()
                    .logoutUrl("/logout")
                    /*
                    Es best practice siempre utilizar el logout como POST si se esta usando csrf.
                    Como en este caso esta disabled utilizamos get.
                    https://docs.spring.io/spring-security/site/docs/4.2.12.RELEASE/apidocs/org/springframework/security/config/annotation/web/configurers/LogoutConfigurer.html
                    */
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login")
                    .permitAll()
                .and()
                .exceptionHandling()
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandlerImpl();
                        accessDeniedHandler.handle(request, response, accessDeniedException);
                    })
                
                //para habilitar la consola de h2
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();    

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