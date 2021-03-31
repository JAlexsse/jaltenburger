package ayi.bookstore.exceptions;

import java.time.ZonedDateTime;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ayi.bookstore.model.BookStoreException;

@ControllerAdvice
public class BookStoreExceptionHandler extends ResponseEntityExceptionHandler{

    /*
    consulta: si quiero implementar try/catch/finally para asegurarme de cerrar el contexto,
    que deberia retornar como response entity(en ambos casos)?
    */

    @ExceptionHandler(value={EntityNotFoundException.class})
    public ResponseEntity<Object> handleBookNotFoundException(EntityNotFoundException e) {

        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ApplicationContext context = new AnnotationConfigApplicationContext(BookStoreException.class);
        BookStoreException bookStoreException = context.getBean(BookStoreException.class); 

        bookStoreException.setMessage(e.getMessage());
        bookStoreException.setHttpStatus(notFound);
        bookStoreException.setTimestamp(ZonedDateTime.now());

        ((AnnotationConfigApplicationContext)context).close();
        
        return new ResponseEntity<Object>(bookStoreException, notFound);
    }


    @ExceptionHandler(value={
        InformationNotCorrectException.class,
        ConstraintViolationException.class,
        DataIntegrityViolationException.class
        })
    public ResponseEntity<Object> handleMissingInformationException(InformationNotCorrectException e) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApplicationContext context = new AnnotationConfigApplicationContext(BookStoreException.class);
        BookStoreException bookStoreException = context.getBean(BookStoreException.class); 
        
        bookStoreException.setMessage(e.getMessage());
        bookStoreException.setHttpStatus(badRequest);
        bookStoreException.setTimestamp(ZonedDateTime.now());

        ((AnnotationConfigApplicationContext)context).close();
        
        return new ResponseEntity<Object>(bookStoreException, badRequest); 
    }
    
}
