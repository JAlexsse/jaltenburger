package ayi.microservicios.serviciooauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ayi.microservicios.commonsusuarios.entity.Usuario;
import ayi.microservicios.serviciooauth.clients.UsuarioFeignClient;

@Service
public class UsuarioService implements UserDetailsService{

    private Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioFeignClient clientFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = clientFeign.findByUsername(username);
        
        if(usuario==null){
            log.error( "Error. No se encuentra al usuario " + username + " en la base de datos.");
            throw new UsernameNotFoundException(
                "Error. No se encuentra al usuario " + username + " en la base de datos."
            );
        }

        List<GrantedAuthority> authorities = usuario.getRoles()
            .stream().map(role -> new SimpleGrantedAuthority(role.getName()))
            .peek(authority -> log.info("Role: " + authority.getAuthority()))
            .collect(Collectors.toList());

        log.info("Usuario autenticado: " + username);
        
        return new User(
            usuario.getUsername(), 
            usuario.getPassword(), 
            usuario.getEnabled(), 
            true, true, true, authorities);
    }
    
}
