package ayi.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ayi.bookstore.entity.Author;
import ayi.bookstore.entity.Book;
import ayi.bookstore.exceptions.EntityNotFoundException;
import ayi.bookstore.repository.AuthorRepository;
import ayi.bookstore.repository.BookRepository;

/*
Best Practice: asegurar en la capa de servicios.
*/

@Service
public class SearchServices {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    /* 
    Busca segun el id proporcionado el libro requerido en la tabla Book.
    Y devuelve el libro.
    De otro modo devuelve una excepcion customizada.
    */
    @PreAuthorize("hasAuthority('book:read')")
    public Book getBookData(int id){

        return bookRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("The id does not match with any book on data base")
        );     
    }

    /* 
    Busca segun el id proporcionado el autor requerido en la tabla Author.
    Y devuelve el autor.
    De otro modo tira una excepcion customizada.
    */
    @PreAuthorize("hasAuthority('author:read')")
    public Author getAuthor(int id){

        return authorRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("The id does not match with any author on data base")
        );       
    }

    /* 
    Busca segun el id proporcionado el autor requerido en la tabla Author.
    Y devuelve el autor.
    De otro modo tira una excepcion customizada.
    */
    @PreAuthorize("hasAuthority('publishing:read')")
    public Author getPublishing(int id){
        
        return authorRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("The id does not match with any publishing on data base")
        );       
    }

    
}
