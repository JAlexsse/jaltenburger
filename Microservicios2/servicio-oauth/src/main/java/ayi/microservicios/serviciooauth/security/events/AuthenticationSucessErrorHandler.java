package ayi.microservicios.serviciooauth.security.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ayi.microservicios.commonsusuarios.entity.Usuario;
import ayi.microservicios.serviciooauth.services.IUsuarioService;
import feign.FeignException;

@Component
public class AuthenticationSucessErrorHandler implements AuthenticationEventPublisher{


    @Autowired
    private IUsuarioService usuarioService;

    private Logger log = LoggerFactory.getLogger(AuthenticationSucessErrorHandler.class);

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        
        if(authentication.getName().equalsIgnoreCase("front-end-app")){
            return;
        }

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String message = "Successful login: " + user.getUsername();

        System.out.println(message);
        log.info(message);

        //Como ingreso a la rama del sucess quiere decir que el usuario existe y no hace falta try/catch
        Usuario usuario = usuarioService.findByUsername(authentication.getName());

        //Si es distinto de 0 y es mayor que cero entonces hay que reiniciarlo por que tuvo exito
        if (usuario.getAttempts() != null && usuario.getAttempts() > 0) {
            
            log.info("La cantidad de intentos era: " + usuario.getAttempts());

            //seteamos en cero
            usuario.setAttempts(0);

            log.info("La cantidad de intentos se seteo en: " + usuario.getAttempts());

            //realizamos el update
            usuarioService.update(usuario, usuario.getId());
        }
       
        
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        String message = "Login error: " + exception.getMessage();

        System.out.println(message);
        log.info(message);

        try {
            Usuario usuario = usuarioService.findByUsername(authentication.getName());

            //seteamos variable en 0 asi no da error
            if(usuario.getAttempts() == null){
                usuario.setAttempts(0);
            }

            //logeamos info antes de la suma
            log.info(
                "El usuario" + usuario.getUsername() + "ha realizado hasta el momento: " 
                + usuario.getAttempts() + " intentos."
            );

            //obtenemos intentos y sumamos uno mas erroneo
            usuario.setAttempts(usuario.getAttempts() + 1);

            //logeamos info luego de la suma
            log.info(
                "El usuario" + usuario.getUsername() + "ha sumado un intento y como resultado tiene: " 
                + usuario.getAttempts() + " intentos."
            );

            //verificamos si la cuenta esta en condiciones de ser deshabilitada
            if(usuario.getAttempts() >= 3){
                log.error(String.format("El usuario %s deshabilitado por máxima cantidad de intentos", usuario.getUsername()));
                usuario.setEnabled(false);
            }

            //actualizamos usuario
            usuarioService.update(usuario, usuario.getId());

        } catch (FeignException e) {
            log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
        }
        
    }
    
}
