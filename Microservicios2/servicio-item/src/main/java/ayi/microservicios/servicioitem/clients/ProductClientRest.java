package ayi.microservicios.servicioitem.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ayi.microservicios.servicioitem.models.Product;

@FeignClient(name="servicio-productos")
public interface ProductClientRest {
    
    @GetMapping("/listar")
    public List<Product> getAll();

    @GetMapping("/producto/{id}")
    public Product getProduct(@PathVariable Long id);
}
