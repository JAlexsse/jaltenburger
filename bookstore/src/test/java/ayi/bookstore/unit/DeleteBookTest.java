package ayi.bookstore.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ayi.bookstore.controller.UpdateController;
import ayi.bookstore.exceptions.EntityNotFoundException;
import ayi.bookstore.services.UpdateServices;

public class DeleteBookTest {
    private UpdateController updateController;
    private UpdateServices updateServices;
    
    private int book_id;

    @Before
    public void setUp() {
        updateServices = Mockito.mock(UpdateServices.class);
        updateController = new UpdateController(updateServices);
    }

    @Test
    public void givenCallDeleteController_whenCorrectId_itShouldReturnWithTrue() throws Exception {
        
        book_id = 1;
                
        Mockito.when(updateServices.deleteBook(book_id))
        .thenReturn(true);

        Boolean response = updateController.deleteBook(book_id);
        assertTrue(response);
    }

    @Test
    public void givenCallModifyBookController_whenIncorrectInfo_itShouldReturnWithFalse() throws Exception {
        
        try {
            Mockito.when(updateServices.deleteBook(book_id))
            .thenReturn(false);
            
        } catch (Exception e) {
            assertEquals(EntityNotFoundException.class, e.getClass());
        } 
        
        Boolean response = updateController.deleteBook(book_id);
        assertFalse(response);
        
    }
}
