package ayi.bookstore.security;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class SecuredCreateControllerTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mvc;

  private String jsonBook = 
  "{\"name\": \"Book Test \", \"price\":123.35, \"author\":1, \"publishing\":2}";

  private String jsonAuthor = "{\"name\": \"Author Test \"}";

  private String jsonPublishing =
  "{\"name\": \"Publishing Test \", \"street\":\"Street Test\", \"number\":123, \"zipcode\":1234}";

  /*
  Testeo de la seguridad de los controllers.
  Solo admin puede acceder a los controladores de Create.
  
  Se usa @WithUseDetails:
  @WithUserDetails would allow us to use a custom UserDetailsService 
  to create our Authentication principal but required the user to exist.
  https://docs.spring.io/spring-security/site/docs/4.2.x/reference/html/test-method.html
  */

  @Before
  public void setup() {
    mvc = MockMvcBuilders
      .webAppContextSetup(context)
      .apply(springSecurity())
      .build();
  }

  @Test
  @WithUserDetails("admin") //usuario existente
  public void givenAuthRequest_whenCreateBookController_shouldSucceedWith200() throws Exception {
    mvc.perform(                                  //se realiza post
      post("/admin/createbook")                   //se proporciona el path
      .content(jsonBook)                          //se proporciona json body
      .contentType(MediaType.APPLICATION_JSON)    //se define tipo de contenido del body
      .accept(MediaType.APPLICATION_JSON)         //se define tipo aceptado
      )
      .andExpect(status().isOk());                //status esperado: 200 OK
  }

  @Test
  @WithUserDetails()
  public void givenRequest_whenCreateBookController_shouldFailWith403() throws Exception {
    mvc.perform(
      post("/admin/createbook")
      .content(jsonBook)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isForbidden());
  }

  @Test
  @WithUserDetails("user")
  public void givenAuthWithNoPermissionRequest_whenCreateBookController_shouldFailWith403() throws Exception {
    mvc.perform(
      post("/admin/createbook")
      .content(jsonBook)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isForbidden());
  }

  @Test
  @WithUserDetails("admin")
  public void givenRequest_whenCreateAuthorController_shouldSucceedWith200() throws Exception {
    mvc.perform(
      post("/admin/createauthor")
      .content(jsonAuthor)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk());
  }

  @Test
  @WithUserDetails()
  public void givenRequest_whenCreateAuthorController_shouldFailWith403() throws Exception {
    mvc.perform(
      post("/admin/createauthor")
      .content(jsonAuthor)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isForbidden());
  }

  @Test
  @WithUserDetails("user")
  public void givenAuthWithNoPermissionRequest_whenCreateAuthorController_shouldFailWith403() throws Exception {
    mvc.perform(
      post("/admin/createauthor")
      .content(jsonAuthor)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isForbidden());
  }

  @Test
  @WithUserDetails("admin")
  public void givenRequest_whenCreatePublishingController_shouldSucceedWith200() throws Exception {
    mvc.perform(
      post("/admin/createpublishing")
      .content(jsonPublishing)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk());
  }

  @Test
  @WithUserDetails()
  public void givenRequest_whenCreatePublishingController_shouldFailWith403() throws Exception {
    mvc.perform(
      post("/admin/createpublishing")
      .content(jsonPublishing)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isForbidden());
  }

  @Test
  @WithUserDetails("user")
  public void givenAuthWithNoPermissionRequest_whenCreatePlublishingController_shouldFailWith403() throws Exception {
    mvc.perform(
      post("/admin/createpublishing")
      .content(jsonPublishing)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isForbidden());
  } 
}
