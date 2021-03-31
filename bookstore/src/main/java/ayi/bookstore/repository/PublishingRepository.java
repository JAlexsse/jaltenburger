package ayi.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ayi.bookstore.entity.Publishing;

@Repository
public interface PublishingRepository extends JpaRepository<Publishing, Integer>{

    @Query(value = "SELECT * FROM PUBLISHING WHERE PUBLISHING_NAME = ?1", nativeQuery = true)
    public Publishing findByPublishingName(String publishing_name);

}
