package ayi.microservicios.serviciooauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import ayi.microservicios.commonsusuarios.entity.Usuario;
import ayi.microservicios.serviciooauth.services.IUsuarioService;

@Component
public class InfoAdicionalToken implements IUsuarioService, TokenEnhancer{

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> info = new HashMap<String, Object>();
        Usuario usuario = usuarioService.findByUsername(authentication.getName());

        info.put("nombre", usuario.getName());
        info.put("apellido", usuario.getLastname());
        info.put("correo", usuario.getEmail());

        //OAuth2AccessToken es muy generico asi que hay que castearlo a una clase mas especifica
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        
        return accessToken;
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioService.findByUsername(username);
    }
    
}
