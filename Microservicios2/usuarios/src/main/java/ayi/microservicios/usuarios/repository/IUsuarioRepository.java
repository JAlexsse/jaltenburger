package ayi.microservicios.usuarios.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ayi.microservicios.usuarios.entity.Usuario;

@RepositoryRestResource(path = "usuarios")
public interface IUsuarioRepository extends PagingAndSortingRepository<Usuario, Long>{
    
    @RestResource(path="/buscar-username")
    public Usuario findByUsername(@Param("usuario") String username);

}
