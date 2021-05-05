package ayi.microservicios.servicioitem.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ayi.microservicios.servicioitem.models.Item;
import ayi.microservicios.servicioitem.models.Product;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Item> findAll() {
        List<Product> products = Arrays.asList(
            restTemplate.getForObject("http://localhost:8081/listar", Product[].class)
        );
        return products.stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("id", id.toString());

        Product product = restTemplate.getForObject("http://localhost:8081/producto/{id}", Product.class, pathVariables);
        return new Item(product, quantity);
    }
    
}
