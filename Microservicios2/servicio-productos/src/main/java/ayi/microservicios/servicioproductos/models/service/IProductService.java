package ayi.microservicios.servicioproductos.models.service;

import java.util.List;

import ayi.microservicios.servicioproductos.models.entity.Product;

public interface IProductService {
    
    public List<Product> findAll();
    public Product findById(Long id);
}
