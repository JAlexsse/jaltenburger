package ayi.bookstore.unit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ayi.bookstore.controller.CreateController;
import ayi.bookstore.exceptions.EntityNotFoundException;
import ayi.bookstore.exceptions.InformationNotCorrectException;
import ayi.bookstore.model.InitElement;
import ayi.bookstore.services.CreateServices;

public class CreateBookTest {
    private CreateController createController;
    private CreateServices createServices;
    
    private InitElement initElement = new InitElement();

    @Before
    public void setUp() {
        createServices = Mockito.mock(CreateServices.class);
        createController = new CreateController(createServices);
    }

    @Test
    public void createBookReturnsSucessWithId() {

        initElement.setName("Test book");
        initElement.setPrice(123.12);
        initElement.setAuthor_id(1);
        initElement.setPublishing_id(1);
        
        Mockito.when(createServices.createBook(
            initElement.getName(), 
            initElement.getAuthor_id(), 
            initElement.getPrice(), 
            initElement.getPublishing_id()))
        .thenReturn("Sucess");

        String response = createController.createBook(initElement);
        Assert.assertEquals("Sucess", response);
                     
    }

    @Test
    public void createBookReturnsSucessWithNames() {

        initElement.setName("Test book");
        initElement.setPrice(123.12);
        initElement.setAuthor_name("Matt Haig");
        initElement.setPublishing_name("ABC MEDIA CORP.");
        
        Mockito.when(createServices.createBook(
            initElement.getName(), 
            initElement.getAuthor_name(), 
            initElement.getPrice(), 
            initElement.getPublishing_name()))
        .thenReturn("Sucess");
        
        String response = createController.createBook(initElement);
        Assert.assertEquals("Sucess", response);           
    }

    @Test
    public void createBookReturnsInformationNotCorrectException() throws InformationNotCorrectException{

        initElement.setName("Test book");
        initElement.setPrice(123.12);
        initElement.setAuthor_name("Matt Haig");
        initElement.setPublishing_name("KIKI FACTORY"); //Se tiene que registrar editorial antes
        
        Mockito.when(createServices.createBook(
            initElement.getName(), 
            initElement.getAuthor_name(), 
            initElement.getPrice(), 
            initElement.getPublishing_name()))
        .thenThrow(InformationNotCorrectException.class);
        
        Assert.assertThrows(
            InformationNotCorrectException.class, 
            () -> createController.createBook(initElement));  
    }

    @Test
    public void createBookReturnsEntityNotFoundException() throws EntityNotFoundException {

        initElement.setName("Test book");
        initElement.setPrice(123.12);
        initElement.setAuthor_id(1);
        initElement.setPublishing_id(35); //id no pertenece a una entidad en base de datos
        
        Mockito.when(createServices.createBook(
            initElement.getName(), 
            initElement.getAuthor_id(), 
            initElement.getPrice(), 
            initElement.getPublishing_id()))
        .thenThrow(EntityNotFoundException.class);
        
        Assert.assertThrows(
            EntityNotFoundException.class, 
            () -> createController.createBook(initElement));  
    }
}
