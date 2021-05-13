package ayi.microservicios.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"ayi.microservicios.commonsusuarios.entity"})
public class UsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuariosApplication.class, args);
	}

}
