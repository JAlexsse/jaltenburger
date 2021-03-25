package ayi.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ayi.bookstore.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{   
}
