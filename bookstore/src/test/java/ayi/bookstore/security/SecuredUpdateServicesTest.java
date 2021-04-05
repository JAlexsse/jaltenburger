package ayi.bookstore.security;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import ayi.bookstore.services.UpdateServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecuredUpdateServicesTest {

    @Autowired
    private UpdateServices updateServices;

    /*
    Testeo de la seguridad en la capa de services.
    Solo admin puede acceder a modificar y eliminar.
    */

    @Test(expected = AccessDeniedException.class)
    @WithUserDetails()
    public void givenNoAuth_whenCallUpdateService_thenThrowsException() {
        updateServices.modifyBook("Test book", 123.12, 1);
    }

    @Test(expected = AccessDeniedException.class)
    @WithUserDetails("user")
    public void givenAuthWithNoPermission_whenCallUpdateService_thenThrowsException() {
        updateServices.modifyBook("Test book", 123.12, 1);
    }

    @Test
    @WithUserDetails("admin")
    public void givenAuthenticated_whenCallUpdateService_thenOk() {
        Assert.assertTrue(updateServices.modifyBook("Test book", 123.12, 1));
    }

    @Test(expected = AccessDeniedException.class)
    @WithUserDetails()
    public void givenNoAuth_whenCallDeleteService_thenThrowsException() {
        updateServices.deleteBook(1);
    }

    @Test(expected = AccessDeniedException.class)
    @WithUserDetails("user")
    public void givenAuthWithNoPermission_whenCallDeleteService_thenThrowsException() {
        updateServices.deleteBook(1);
    }

    @Test
    @WithUserDetails("admin")
    public void givenAuthenticated_whenCallDeleteService_thenOk() {
        Assert.assertTrue(updateServices.deleteBook(1));
    } 
    
}
