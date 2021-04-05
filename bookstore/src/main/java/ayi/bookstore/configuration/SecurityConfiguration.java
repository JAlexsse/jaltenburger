package ayi.bookstore.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ayi.bookstore.security.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        /*
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) 
            Es recomendable utilizar csrf cuando usuarios van a poder procesar por browser informacion.
            En estos casos el withHttpOnlyFalse no permite 
            que la cookie sea accesible para el cliente, pero hay
            que implementar logica para pasar el token por cada request.
        */

        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/home", "/about").permitAll()
                    .antMatchers("/h2-console/**").hasRole("ADMIN")
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers("/user/**").hasAnyRole("USER")
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
                .and()
                .headers().frameOptions().disable();    

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService(){

        ApplicationContext context = new AnnotationConfigApplicationContext(EncoderBeanConfiguration.class);
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);    

        UserDetails admin = User.builder()
            .username("admin")
            .password(encoder.encode("password"))
            .authorities(BookstoreUserRole.ADMIN.getGrantedAuthorities())
            .build();

        UserDetails user = User.builder()
            .username("user")
            .password(encoder.encode("password"))
            .authorities(BookstoreUserRole.USER.getGrantedAuthorities())
            .build();
        
        ((AnnotationConfigApplicationContext)context).close();

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