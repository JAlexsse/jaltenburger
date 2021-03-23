package ayi.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ayi.bookstore.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
