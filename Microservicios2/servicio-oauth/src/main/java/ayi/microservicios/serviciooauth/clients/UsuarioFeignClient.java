package ayi.microservicios.serviciooauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ayi.microservicios.commonsusuarios.entity.Usuario;

@FeignClient(name = "servicio-usuarios")
public interface UsuarioFeignClient {
    @GetMapping("/usuarios/search/buscar-username")
    public Usuario findByUsername(@RequestParam String username);
}
