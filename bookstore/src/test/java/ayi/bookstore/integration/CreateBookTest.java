package ayi.bookstore.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ayi.bookstore.exceptions.InformationNotCorrectException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class CreateBookTest {
    @Autowired
  private WebApplicationContext context;

  private MockMvc mvc;

  private String jsonBook = 
  "{\"name\": \"Book Test \", \"price\":123.35, \"author\":1, \"publishing\":2}";

  @Before
  public void setup() {
    mvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply(springSecurity())
      .build();
  }

  @Test
  @WithUserDetails("admin")
  public void givenCallCreateBookController_whenCorrectInfo_itShouldReturnSucess() throws Exception{

    jsonBook = 
    "{\"name\": \"Book Test \", \"price\":123.35, \"author\":1, \"publishing\":2}";

    MvcResult result = mvc
        .perform(                                  
            post("/admin/createbook")                   
            .content(jsonBook)                          
            .contentType(MediaType.APPLICATION_JSON)    
            .accept(MediaType.APPLICATION_JSON)        
        )
        .andExpect(status().isOk())
        .andReturn();
    
    assertEquals("Sucess", result.getResponse().getContentAsString());
       
  }

  @Test
  @WithUserDetails("admin")
  public void givenCallCreateBookController_whenIncorrectInfo_itShouldFail() throws Exception{

    try {
        mvc
        .perform(                                  
            post("/admin/createbook")                   
            .content(jsonBook)                          
            .contentType(MediaType.APPLICATION_JSON)    
            .accept(MediaType.APPLICATION_JSON)        
        )
        .andReturn();
    } catch (Exception e) {
        assertEquals(InformationNotCorrectException.class, e.getClass());
    }
       
  }

}
