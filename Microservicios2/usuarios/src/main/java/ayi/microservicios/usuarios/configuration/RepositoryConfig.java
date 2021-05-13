package ayi.microservicios.usuarios.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import ayi.microservicios.commonsusuarios.entity.Role;
import ayi.microservicios.commonsusuarios.entity.Usuario;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{
    
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config){
        config.exposeIdsFor(Usuario.class, Role.class);
    }
}
