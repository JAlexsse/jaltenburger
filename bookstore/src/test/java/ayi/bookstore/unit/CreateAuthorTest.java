package ayi.bookstore.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import ayi.bookstore.controller.CreateController;
import ayi.bookstore.model.InitElement;
import ayi.bookstore.services.CreateServices;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class CreateAuthorTest {

    private CreateController createController;
    private CreateServices createServices;

    private String bookName;
    private InitElement initElement = new InitElement();

    @Before
    public void setUp() {
        createServices = Mockito.mock(CreateServices.class);
        createController = new CreateController(createServices);
    }

    @Test
    public void givenCallCreateAuthorController_whenInfoIsCorrect_itShouldReturnSucess() throws Exception{
        
        bookName = "Test Author";
        initElement.setAuthor_name(bookName);

        Mockito.when(createServices.createAuthor(bookName)).thenReturn("Sucess");
        
        String response = createController.createAuthor(initElement);

        assertEquals("Sucess", response);
    }

    @Test
    public void givenCallCreateAuthorController_whenInfoIsEmpty_itShouldFail() throws Exception{
        
        //Se estan pasando nulls por lo que falla

        Mockito.when(createServices.createAuthor(bookName)).thenReturn("It didn't work.");

        String response = createController.createAuthor(initElement);

        assertEquals("It didn't work.", response);
    }

    @Test
    public void givenCallCreateAuthorController_whenInfoIsIncorrect_itShouldFail() throws Exception{
        
        initElement.setPublishing_name("Test Publishing"); //No es informacion correcta

        Mockito.when(createServices.createAuthor(bookName)).thenReturn("It didn't work.");

        String response = createController.createAuthor(initElement);

        assertEquals("It didn't work.", response);
    }
}
