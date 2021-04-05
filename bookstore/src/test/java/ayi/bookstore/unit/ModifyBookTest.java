package ayi.bookstore.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ayi.bookstore.controller.UpdateController;
import ayi.bookstore.exceptions.InformationNotCorrectException;
import ayi.bookstore.model.InitElement;
import ayi.bookstore.services.UpdateServices;

public class ModifyBookTest {
    private UpdateController updateController;
    private UpdateServices updateServices;
    
    private InitElement initElement = new InitElement();
    private int book_id;
    private String book_name = "Test book";
    private double price = 123.12;

    @Before
    public void setUp() {
        updateServices = Mockito.mock(UpdateServices.class);
        updateController = new UpdateController(updateServices);
    }

    @Test
    public void givenCallModifyBookController_whenCorrectInfo_itShouldReturnWithTrue() throws Exception {
        
        book_id = 1;
        initElement.setName(book_name);
        initElement.setPrice(price);
        initElement.setBook_id(book_id);
        
        Mockito.when(updateServices.modifyBook(
            initElement.getName(), 
            initElement.getPrice(),
            initElement.getBook_id()))
        .thenReturn(true);

        Boolean response = updateController.modifyBook(initElement);
        assertTrue(response);
    }

    @Test
    public void givenCallModifyBookController_whenWrongId_itShouldReturnWithFalse() throws Exception {
        
        book_id = 35;
        initElement.setName(book_name);
        initElement.setPrice(price);
        initElement.setBook_id(book_id);
        
        try {
            Mockito.when(updateServices.modifyBook(
                initElement.getName(), 
                initElement.getPrice(),
                initElement.getBook_id()))
            .thenReturn(false);
            
        } catch (Exception e) {
            assertEquals(InformationNotCorrectException.class, e.getClass());
        } 
        
        Boolean response = updateController.modifyBook(initElement);
        assertFalse(response);
        
    }

    @Test
    public void givenCallModifyBookController_whenIncorrectInfo_itShouldReturnWithFalse() throws Exception {
        
        try {
            Mockito.when(updateServices.modifyBook(
                initElement.getName(), 
                initElement.getPrice(),
                initElement.getBook_id()))
            .thenReturn(false);
            
        } catch (Exception e) {
            assertEquals(InformationNotCorrectException.class, e.getClass());
        } 
        
        Boolean response = updateController.modifyBook(initElement);
        assertFalse(response);
        
    }

}
