package ayi.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ayi.bookstore.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query(value = "SELECT * FROM AUTHOR WHERE AUTHOR_NAME = ?1", nativeQuery = true)
    public Author findByAuthorName(String author_name);

}
