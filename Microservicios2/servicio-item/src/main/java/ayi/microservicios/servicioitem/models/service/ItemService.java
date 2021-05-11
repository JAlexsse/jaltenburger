package ayi.microservicios.servicioitem.models.service;

import java.util.List;

import ayi.microservicios.servicioitem.models.Item;
import ayi.microservicios.servicioitem.models.Product;

public interface ItemService {
    
    public List<Item> findAll();
    public Item findById(Long id, Integer quantity);
    public Product save(Product product);
    public Product update(Product product, Long id);
    public void delete(Long id);

}
