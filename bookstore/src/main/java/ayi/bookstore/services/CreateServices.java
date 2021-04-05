package ayi.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ayi.bookstore.entity.Author;
import ayi.bookstore.entity.Book;
import ayi.bookstore.entity.Publishing;
import ayi.bookstore.exceptions.EntityNotFoundException;
import ayi.bookstore.exceptions.InformationNotCorrectException;
import ayi.bookstore.model.Adress;
import ayi.bookstore.repository.AuthorRepository;
import ayi.bookstore.repository.BookRepository;
import ayi.bookstore.repository.PublishingRepository;

/*
Best Practice: asegurar en la capa de servicios.
*/

@Service
public class CreateServices {
    
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
    Si tiene exito devuelve el String Sucess, de otro modo devuelve el String Failed.
    */
    @PreAuthorize("hasAuthority('book:write')")
    public String createBook(String name, int author_id, double price, int publishing_id){
        try {

            Author author = authorRepository.findById(author_id)
            .orElseThrow(() -> new EntityNotFoundException("The id does not match with any author on data base"));

            Publishing publishing = publishingRepository.findById(publishing_id)
            .orElseThrow(() -> new EntityNotFoundException("The id does not match with any author on data base"));
            
            Book newBook = new Book(name, author, price, publishing);
            bookRepository.save(newBook);

            return "Sucess";
            
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /*
    SOBRECARGA DE PARAMETROS.
    Recibe un nombre de libro, tambien un nombre de autor y de una editorial del cual no sabemos si existen.
    Busca en las instancias de Author y Publishing una instancia de mismo nombre que las pasadas como parámetros. 
    Si no las encuentra las crea.
    Crea una instancia de Book con el nombre, el autor y la editorial.
    Luego guarda esta instancia en la tabla de Book. 
    Si tiene exito devuelve el String Sucess, de otro modo devuelve el String Failed.
    */
    @PreAuthorize("hasAuthority('book:write')")
    public String createBook(String name, String author_name, double price, String publishing_name){
        try { 
            Author author = authorRepository.findByAuthorName(author_name);
            Publishing publishing = publishingRepository.findByPublishingName(publishing_name);

            if(author != null){

            }else{
                createAuthor(author_name);
                author = authorRepository.findByAuthorName(author_name);
            }

            if(publishing != null){

            }else{
                throw new InformationNotCorrectException("The publishing has to be registered before.");
            }
            
            Book newBook = new Book(name, author, price, publishing);
            bookRepository.save(newBook);

            return "Sucess";
            
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /* 
    Crea una instancia de Publishing con el nombre proporcionado como parametro
    y luego la guarda en la tabla de Publishing.
    Si tiene éxito devuelve la información guardada: id y nombre.
    Si no lo tiene devuelve: It didn't work.
    */
    @PreAuthorize("hasAuthority('publishing:write')")
    public boolean createPublishing(String name, int number, String street, int zipCode){
        
        ApplicationContext context = new AnnotationConfigApplicationContext(Adress.class);

        try {
            Adress adress = context.getBean(Adress.class);

            adress.setNumber(number);
            adress.setStreet(street);
            adress.setZipCode(zipCode);
            
            Publishing newPublishing = new Publishing(name, adress);
            publishingRepository.save(newPublishing);

            return true;
            
        } catch (Exception e) {
            throw new InformationNotCorrectException("The information provided is not correct.");
        }   finally {
            ((AnnotationConfigApplicationContext)context).close();
        }  
    }

    /* 
    Crea una instancia de Author con el nombre proporcionado por parametros,
    y luego la guarda en la tabla de Publishing.
    Si tiene éxito devuelve la información guardada: id y nombre.
    Si no lo tiene devuelve: It didn't work.
    */
    @PreAuthorize("hasAuthority('author:write')")
    public String createAuthor(String name){
        try {

            Author newAuthor = new Author(name);
            authorRepository.save(newAuthor);

            return "Sucess";
            
        } catch (Exception e) {
            return "It didn't work.";
        }     
    }
 
}
