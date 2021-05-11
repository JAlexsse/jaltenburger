package ayi.microservicios.servicioitem.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ayi.microservicios.servicioitem.models.Item;
import ayi.microservicios.servicioitem.models.Product;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Item> findAll() {
        List<Product> products = Arrays.asList(
            restTemplate.getForObject("http://servicio-productos/listar", Product[].class)
        );
        return products.stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());

        Product product = restTemplate.getForObject("http://servicio-productos/producto/{id}", Product.class, pathVariables);
        return new Item(product, quantity);
    }

    @Override
    public Product save(Product product) {
        HttpEntity<Product> request = new HttpEntity<Product>(product);
        ResponseEntity<Product> responseEntity = restTemplate.exchange("http://servicio-productos/crear", HttpMethod.POST, request, Product.class);
        return responseEntity.getBody();
    }

    @Override
    public Product update(Product product, Long id) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());
        
        HttpEntity<Product> request = new HttpEntity<Product>(product);
        ResponseEntity<Product> responseEntity = restTemplate.exchange(
            "http://servicio-productos/editar/{id}", HttpMethod.PUT, request, Product.class, pathVariables);
        return responseEntity.getBody();
    }

    @Override
    public void delete(Long id) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());

        restTemplate.delete("http://servicio-productos/eliminar/{id}", pathVariables);
        
    }
    
}
