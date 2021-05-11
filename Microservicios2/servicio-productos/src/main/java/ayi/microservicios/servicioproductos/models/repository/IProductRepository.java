package ayi.microservicios.servicioproductos.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ayi.microservicios.commons.entity.Product;

@Repository
public interface IProductRepository extends CrudRepository<Product, Long>{
    
}
