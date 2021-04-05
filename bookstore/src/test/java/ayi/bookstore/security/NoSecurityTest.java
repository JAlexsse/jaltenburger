package ayi.bookstore.security;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ayi.bookstore.controller.SecurityController;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SecurityController.class)
public class NoSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenNoAuth_whenPathWithoutSecurity_shouldBeOk() throws Exception {
        
        this.mockMvc.perform(get("/login"))
            .andExpect(status().isOk());

        this.mockMvc.perform(get("/home"))
            .andExpect(status().isOk());
        
        this.mockMvc.perform(get("/about"))
            .andExpect(status().isOk());
    }
    
}
