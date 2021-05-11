package ayi.microservicios.servicioitem.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ayi.microservicios.servicioitem.models.Product;

@FeignClient(name="servicio-productos")
public interface ProductClientRest {
    
    @GetMapping("/listar")
    public List<Product> getAll();

    @GetMapping("/producto/{id}")
    public Product getProduct(@PathVariable Long id);

    @PostMapping("/crear")
    public Product createProduct(@RequestBody Product product);

    @PutMapping("/editar/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id);

    @DeleteMapping("/eliminar/{id}")
    public void deleteProduct(@PathVariable Long id);
}
