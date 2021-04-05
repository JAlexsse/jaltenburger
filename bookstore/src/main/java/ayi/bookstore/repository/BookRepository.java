package ayi.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ayi.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book>{   

    @Query(value = "SELECT * FROM BOOK WHERE BOOK_NAME = ?1", nativeQuery = true)
    public Book findByBookName(String book_name);

}
