package ayi.microservicios.servicioitem.models.service;

import java.util.List;

import ayi.microservicios.servicioitem.models.Item;

public interface ItemService {
    
    public List<Item> findAll();
    
    public Item findById(Long id, Integer quantity);
}
