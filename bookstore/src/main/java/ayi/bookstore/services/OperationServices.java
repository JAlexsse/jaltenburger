package ayi.bookstore.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ayi.bookstore.model.Author;
import ayi.bookstore.model.Book;
import ayi.bookstore.repository.AuthorRepository;
import ayi.bookstore.repository.BookRepository;

@Service
public class OperationServices {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    /* 
    Busca segun el id proporcionado el libro requerido en la tabla Book.
    Y devuelve el libro.
    */
    public Book getBookData(int id){

        Optional<Book> searchedBook = bookRepository.findById(id);

        Book book = searchedBook.get();

        return book;     
    }

    /* 
    Busca segun el id proporcionado el autor requerido en la tabla Author.
    Y devuelve el autor.
    */
    public Author getAuthor(int id){

        Optional<Author> author = authorRepository.findById(id);
        Author searchedAuthor = author.get();
        
        return searchedAuthor;   
    }


    /* 
    Busca el libro segun la id proporcionada, y luego lo modifica con el
    nuevo nombre proporcionado y lo guarda en base de datos.
    Si tiene exito devuelve: Sucess.
    Si no lo tiene: Failed.
    */
    public String modifyBookName(String name, int id) {
        try {
            Optional<Book> searchedBook = bookRepository.findById(id);

            Book book = searchedBook.get();

            book.setBook_name(name);

            bookRepository.save(book);

            return "Sucess";
        } catch (Exception e) {
            return "Failed";
        }
    }

    /* 
    Busca el libro segun la id proporcionada, y luego elimina el libro requerido.
    Si tiene exito devuelve: Sucess.
    Si no lo tiene: Failed.
    */
    public String deleteBook(int id) {
        try {
            Optional<Book> searchedBook = bookRepository.findById(id);

            Book book = searchedBook.get();

            bookRepository.delete(book);

            return "Sucess";
        } catch (Exception e) {
            return "Failed";
        }
    }
    
}
