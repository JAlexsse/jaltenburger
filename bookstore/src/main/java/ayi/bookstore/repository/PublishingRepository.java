package ayi.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ayi.bookstore.model.Publishing;

@Repository
public interface PublishingRepository extends JpaRepository<Publishing, Integer>{
    
}
