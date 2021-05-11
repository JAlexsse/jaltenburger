package ayi.microservicios.servicioproductos.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ayi.microservicios.commons.entity.Product;
import ayi.microservicios.servicioproductos.models.service.IProductService;

@RestController
@PropertySource(value = "application.properties")
public class ProductController {
    
    @Autowired
    private  Environment environment; //obtener informacion de properties, para poder saber el puerto

    @Value("${server.port}") //Value utiliza los valores en el application.properties
    private Integer port;

    @Autowired
    private IProductService iProductService;

    @GetMapping("/listar")
    public List<Product> getAll(){
        return iProductService.findAll().stream().map(
            product -> {
                //con enviroment
                product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
                return product;
            }
        ).collect(Collectors.toList());
    }

    @GetMapping("/producto/{id}")
    public Product getProduct(@PathVariable Long id) throws Exception{
        Product product = iProductService.findById(id);
        //product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        product.setPort(port); //con value

        //simulacion de error para probar Hystrix
        /*
        boolean ok = false;
        if (ok == false) {
            throw new Exception("No se encontro producto.");
        }
        */

        //para probar la configuracion de timeouts
        /*
        try {
            Thread.sleep(2000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        return product;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        return iProductService.save(product);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
        Product productDB = iProductService.findById(id);
        productDB.setName(product.getName());
        productDB.setPrice(product.getPrice());
        return iProductService.save(productDB);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        iProductService.deleteById(id);
    }
    
}
