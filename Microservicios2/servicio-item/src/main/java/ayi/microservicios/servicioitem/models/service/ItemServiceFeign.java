package ayi.microservicios.servicioitem.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ayi.microservicios.servicioitem.clients.ProductClientRest;
import ayi.microservicios.servicioitem.models.Item;
import ayi.microservicios.servicioitem.models.Product;

@Service("serviceFeign")
@Primary //da prioridad, por defecto
public class ItemServiceFeign implements ItemService{

    @Autowired
    private ProductClientRest clientFeign;

    @Override
    public List<Item> findAll() {
        return clientFeign.getAll().stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        return new Item(clientFeign.getProduct(id), quantity);
    }

    @Override
    public Product save(Product product) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Product update(Product product, Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }
    
}
