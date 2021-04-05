package ayi.bookstore.unit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import ayi.bookstore.controller.CreateController;
import ayi.bookstore.exceptions.InformationNotCorrectException;
import ayi.bookstore.model.InitElement;
import ayi.bookstore.services.CreateServices;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class CreatePublishingTest {
    private CreateController createController;
    private CreateServices createServices;

    private String publishingName;
    private String street;
    private int number;
    private int zipCode;
    private InitElement initElement = new InitElement();

    @Before
    public void setUp() {
        createServices = Mockito.mock(CreateServices.class);
        createController = new CreateController(createServices);
    }

    @Test
    public void givenCallCreatePublishingController_whenInfoIsCorrect_itShouldReturnTrue() throws Exception{
        
        publishingName = "Test Publishing";
        street = "Test Street";
        number = 123;
        zipCode = 12345;

        initElement.setPublishing_name(publishingName);
        initElement.setStreet(street);
        initElement.setNumber(number);
        initElement.setZipCode(zipCode);

        Mockito
            .when(
                createServices.createPublishing(publishingName, number, street, zipCode)
            )
            .thenReturn(true);
        
        Boolean response = createController.createPublihsing(initElement);

        assertTrue(response);
    }

    @Test
    public void givenCallCreatePublishingController_whenInfoIsEmpty_itShouldReturnFalse() throws InformationNotCorrectException{
        
        //Se estan pasando nulls por lo que falla

        Mockito
            .when(
                createServices.createPublishing(publishingName, number, street, zipCode)
            )
            .thenThrow(InformationNotCorrectException.class);
        
        Boolean response = createController.createPublihsing(initElement);

        assertFalse(response);
    }

    @Test
    public void givenCallCreateAuthorController_whenInfoIsIncorrect_itShouldReturnFalse() throws InformationNotCorrectException{
        
        initElement.setAuthor_name("Test Author"); //No es informacion correcta

        Mockito
            .when(
                createServices.createPublishing(publishingName, number, street, zipCode)
            )
            .thenReturn(false);

        Boolean response = createController.createPublihsing(initElement);

        assertFalse(response);
    }
    
}
