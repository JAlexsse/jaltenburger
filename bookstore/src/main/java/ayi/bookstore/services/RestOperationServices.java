package ayi.bookstore.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ayi.bookstore.model.Author;
import ayi.bookstore.model.Book;
import ayi.bookstore.model.Publishing;
import ayi.bookstore.repository.AuthorRepository;
import ayi.bookstore.repository.BookRepository;
import ayi.bookstore.repository.PublishingRepository;

@Service
public class RestOperationServices {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublishingRepository publishingRepository;

    /*
    Recibe un nombre de libro, una id de un autor existente y una id de editorial.
    Busca las instancias de Author y Publishing correspondientes a las id pasadas como parámetros. 
    Crea una instancia de Book con el nombre, el autor y la editorial.
    Luego guarda esta instancia en la tabla de Book. 
    Si tiene exito devuelve el String Sucess, de otro modo devuelve el String It didn't work.
    */
    public String createBook(String name, int author_id, int publishing_id){
        try {

            Optional<Author> authorSearched = authorRepository.findById(author_id);
            Author author = authorSearched.get();

            Optional<Publishing> publishingSearched = publishingRepository.findById(publishing_id);
            Publishing publishing = publishingSearched.get();

            Book newBook = new Book(name, author, publishing);
            bookRepository.save(newBook);

            return "Sucess";
            
        } catch (Exception e) {
            return "It didn't work.";
        }
    }

    /* 
    Crea una instancia de Publishing con el nombre proporcionado como parametro
    y luego la guarda en la tabla de Publishing.
    Si tiene éxito devuelve la información guardada: id y nombre.
    Si no lo tiene devuelve: It didn't work.
    */
    public String createPublishing(String name){
        try {
            Publishing newPublishing = new Publishing(name);
            publishingRepository.save(newPublishing);

            return "Sucess: id: " + newPublishing.getPublishing_id() + " name: " + newPublishing.getPublishing_name();
            
        } catch (Exception e) {
            return "It didn't work.";
        }     
    }

    /* 
    Crea una instancia de Author con el nombre proporcionado por parametros,
    y luego la guarda en la tabla de Publishing.
    Si tiene éxito devuelve la información guardada: id y nombre.
    Si no lo tiene devuelve: It didn't work.
    */
    public String createAuthor(String name){
        try {
            Author newAuthor = new Author(name);
            authorRepository.save(newAuthor);

            return "Sucess: id: " + newAuthor.getAuthor_id() + " name: " + newAuthor.getAuthor_name();
            
        } catch (Exception e) {
            return "It didn't work.";
        }     
    }
 
}
