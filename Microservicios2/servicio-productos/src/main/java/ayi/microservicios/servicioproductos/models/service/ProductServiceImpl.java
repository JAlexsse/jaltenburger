package ayi.microservicios.servicioproductos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ayi.microservicios.servicioproductos.models.entity.Product;
import ayi.microservicios.servicioproductos.models.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductRepository iProductRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>)iProductRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return iProductRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional //sin read only por que es de escritura
    public Product save(Product product) {
        return iProductRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        iProductRepository.deleteById(id); 
    }
    
}
