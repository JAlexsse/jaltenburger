package ayi.bookstore.integration;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ayi.bookstore.exceptions.EntityNotFoundException;

/*
Testing con controllers que implementan Model.
https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-normal-controllers/
*/

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class SearchBookTest {

    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mvc;

    private int id;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();
    }
    
    @Test
    @WithUserDetails("user")
    public void givenCallSearchBookController_whenExistingId_itShouldReturnWith200() throws Exception{
        
        id = 1;

        mvc
            .perform(get("/user/searchbook/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("bookTemplate"))
                .andExpect(model().attribute("book", hasProperty("id", is(1))))
                .andExpect(model().attribute("book", hasProperty("book_name", is("Wish Upon a Lantern"))))
                .andExpect(model().attribute("book", hasProperty("price", is(134.56))));
    }

    @Test
    @WithUserDetails("user")
    public void givenCallSearchBookController_whenInvalidId_itShouldFail() throws Exception{
        
        id = 35; //id incorrecta

        try {
            mvc
            .perform(get("/user/searchbook/" + id));   
        } catch (Exception e) {
            assertEquals(EntityNotFoundException.class, e.getClass());
        }
        
    }
}
