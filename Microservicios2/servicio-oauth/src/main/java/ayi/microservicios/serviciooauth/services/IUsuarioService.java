package ayi.microservicios.serviciooauth.services;

import ayi.microservicios.commonsusuarios.entity.Usuario;

public interface IUsuarioService {
    public Usuario findByUsername(String username);

    public Usuario update(Usuario usuario, Long id);
}