package ayi.microservicios.servicioproductos.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ayi.microservicios.servicioproductos.models.entity.Product;
import ayi.microservicios.servicioproductos.models.service.IProductService;

@RestController
public class ProductController {
    
    @Autowired
    private IProductService iProductService;

    @GetMapping("/listar")
    public List<Product> getAll(){
        return iProductService.findAll();
    }

    @GetMapping("/producto/{id}")
    public Product getProduct(@PathVariable Long id){
        return iProductService.findById(id);
    }
}
