package ayi.microservicios.servicioitem.Controllers;

import java.util.List;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ayi.microservicios.servicioitem.models.Item;
import ayi.microservicios.servicioitem.models.Product;
import ayi.microservicios.servicioitem.models.service.ItemService;

@RestController
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class ItemController {
    
    @Autowired
    /*
    Como hay dos implementaciones de ItemService hay que 
    especificar cual debe traer
    Para utilizar la implementacion de RestTemplate cambiar 
    "serviceFeign" -> "serviceRestTemplate"
    */
    @Qualifier("serviceFeign") 
    private ItemService itemService;

    @GetMapping("/listar")
    public List<Item> getAll(){
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "alternativeMethod")
    @GetMapping("/detalle/{id}/cantidad/{quantity}")
    public Item details(@PathVariable Long id, @PathVariable Integer quantity){
        return itemService.findById(id, quantity);
    }

    public Item alternativeMethod(Long id, Integer quantity){
        //en caso de error retornamos un Item alternativo
        Product product = new Product();
        Item item = new Item();
        
        product.setId(id);
        product.setName("Producto alternativo");
        product.setPrice(123.123);

        item.setProduct(product);
        item.setQuantity(quantity);
        return item;
    }
}
