package ayi.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import ayi.bookstore.entity.Book;
import ayi.bookstore.exceptions.EntityNotFoundException;
import ayi.bookstore.exceptions.InformationNotCorrectException;
import ayi.bookstore.repository.BookRepository;

/*
Best Practice: asegurar en la capa de servicios.
*/

@Service
public class UpdateServices {

    @Autowired
    private BookRepository bookRepository;

    /* 
    Busca el libro con el metodo getBookData(int id) segun la id proporcionada, y luego lo modifica con el
    nuevo nombre proporcionado y lo guarda en base de datos.
    Si tiene exito devuelve: Sucess.
    Si no lo tiene: Failed.
    */
    @PreAuthorize("hasAuthority('book:write')")
    public boolean modifyBook(String name, double price, int id) {
        
        try {
            Book book = bookRepository.findById(id).get();
            book.setBook_name(name);
            book.setPrice(price);
            book = bookRepository.saveAndFlush(book);

            return true;
        } catch (Exception e) {
            throw new InformationNotCorrectException("The information provided was not correct.");
        }
        
    }

    /* 
    Busca el libro con el metodo getBookData(int id) segun la id proporcionada, y luego elimina el libro requerido.
    Si tiene exito devuelve: Sucess.
    Si no lo tiene: Failed.
    */
    @PreAuthorize("hasAuthority('book:write')")
    public boolean deleteBook(int id) {
        try {

            bookRepository.deleteById(id);

            return true;
        } catch (Exception e) {
            throw new EntityNotFoundException("The id does not match with any book on data base");
        }
    }
    
}
