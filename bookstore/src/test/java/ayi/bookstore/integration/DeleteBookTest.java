package ayi.bookstore.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ayi.bookstore.exceptions.EntityNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class DeleteBookTest {
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
    @WithUserDetails("admin")
    public void givenCallDeleteBookController_whenExistingId_itShouldReturnTrue() throws Exception{
        
        id = 1;

        MvcResult response = mvc
            .perform(delete("/admin/deletebook/" + id))
            .andExpect(status().isOk())
            .andReturn();
        
        assertEquals("true", response.getResponse().getContentAsString());

    }

    @Test
    @WithUserDetails("admin")
    public void givenCallDeleteBookController_whenIncorrectId_itShouldFail() throws Exception{
        
        id = 35; //la id no existe

        try {
            mvc
            .perform(delete("/admin/deletebook/" + id))
            .andExpect(status().isNotFound())
            .andReturn();
            
        } catch (Exception e) {
            assertEquals(EntityNotFoundException.class, e.getClass());
        }

    }
    
}
