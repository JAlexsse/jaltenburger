package ayi.microservicios.servicioitem.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ayi.microservicios.servicioitem.models.Item;
import ayi.microservicios.servicioitem.models.Product;
import ayi.microservicios.servicioitem.models.service.ItemService;

@RefreshScope
@RestController
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class ItemController {
    
    private static Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private Environment environment;

    @Autowired
    /*
    Como hay dos implementaciones de ItemService hay que 
    especificar cual debe traer
    Para utilizar la implementacion de RestTemplate cambiar 
    "serviceFeign" -> "serviceRestTemplate"
    */
    @Qualifier("serviceFeign") 
    private ItemService itemService;

    @Value("${configuracion.texto}")
    private String text;

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

    @GetMapping("/obtener-config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") String port){
        
        log.info(text);
        
        Map<String, String> json = new HashMap<>();
        json.put("texto", text);
        json.put("puerto", port);

        //Nos aseguramos que vengan elementos en el arreglo y que este sea profile development
        if(environment.getActiveProfiles().length > 0 && environment.getActiveProfiles()[0].equals("development")){
            //agregamos los campos que perteneces al profile al json
            json.put("autor.name", environment.getProperty("configuracion.autor.nombre"));
            json.put("autor.email", environment.getProperty("configuracion.autor.email"));
        }

        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK );
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return itemService.save(product);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return itemService.update(product, id);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        itemService.delete(id);
    }
}
