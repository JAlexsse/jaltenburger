package ayi.microservicios.servicioproductos.models.service;

import java.util.List;

import ayi.microservicios.commons.entity.Product;

public interface IProductService {
    
    public List<Product> findAll();
    public Product findById(Long id);
    public Product save(Product product);
    public void deleteById(Long id);
}
