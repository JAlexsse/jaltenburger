package ayi.bookstore.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import ayi.bookstore.entity.Author;
import ayi.bookstore.entity.Book;
import ayi.bookstore.entity.Publishing;
import ayi.bookstore.specification.BookSpecification;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublishingRepository publishingRepository;

    private BookSpecification bookSpecification = new BookSpecification();

    private Publishing publishingTest = new Publishing();
    private Author authorTest = new Author();
    private Book bookTest = new Book();
    private List<Book> booksTest = new ArrayList<Book>();
    
    @Before
    public void setUp() {
        
        publishingTest = publishingRepository.findById(1).get();
        authorTest = authorRepository.findById(1).get();

        bookTest.setBook_name("Test");
        bookTest.setAuthor(authorTest);
        bookTest.setPrice(123.12);
        bookTest.setPublishing(publishingTest);

    }

    @Test
    public void saveBook_withCorrectInfo_ShouldSucceed() {
        assertDoesNotThrow(()-> bookRepository.save(bookTest));
    }

    @Test
    public void findByName_WithValidName_ShouldSucceed() {
        String bookName = bookRepository.findByBookName("One Upon a Time").getBook_name();
        assertEquals("One Upon a Time", bookName);
    }

    @Test
    public void findByName_WithInvalidName_ShouldFail() {

        try {
            bookRepository.findByBookName("Does not exist"); 
        } catch (Exception e) {
            assertEquals(NotFoundException.class, e.getClass());
        }
    }

    @Test
    public void deleteById_withValidId_shouldSucceed() {
        bookRepository.deleteById(1);

        try {
            bookRepository.findById(1);
        } catch (Exception e) {
            assertEquals(NotFoundException.class, e.getClass());
        }
    }

    @Test
    public void getBooksByAuthor_WithValidId_ShouldSucceed() {

        booksTest = bookRepository.findAll(
            Specification.where(
                bookSpecification
                    .getUserSpecificationByAuthorOrPublishing("author", 3)
            )
        );
        
        assertEquals(2, booksTest.size());

        for (Book book : booksTest) {
            assertEquals(3, book.getAuthor().getAuthor_id());
        }   
    }

    @Test
    public void getBooksByPublishing_WithValidId_ShouldSucceed() {

        booksTest = bookRepository.findAll(
            Specification.where(
                bookSpecification
                    .getUserSpecificationByAuthorOrPublishing("publishing", 1)
            )
        );
        
        assertEquals(1, booksTest.size());

        for (Book book : booksTest) {
            assertEquals(1, book.getPublishing().getPublishing_id());
        }
        
    }

    @Test
    public void getBooksByPublishing_WithInvalidId_ShouldHaveNoResults() {

        booksTest = bookRepository.findAll(
            Specification.where(
                bookSpecification
                    .getUserSpecificationByAuthorOrPublishing("publishing", 5) //id no existe
            )
        );
        
        assertEquals(0, booksTest.size());
        
    }

    
    
}
