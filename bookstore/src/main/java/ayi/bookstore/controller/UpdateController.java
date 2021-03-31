package ayi.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ayi.bookstore.model.InitElement;
import ayi.bookstore.services.UpdateServices;

@RestController
public class UpdateController {
    
    @Autowired
    private UpdateServices updateServices;

    /* 
    Llama al servicio para cambiar el nombre del libro del cual se proporciona la id.
    */
    @PutMapping("/admin/modifybook")
    @PreAuthorize("hasAuthority('book:write')")
    public boolean modifyBookName(@RequestBody InitElement initElement) {
        
        return updateServices.modifyBookName(initElement.getName(), initElement.getPrice(), initElement.getBook_id());
              
    }

    /* 
    Llama al servicio para cambiar el nombre del libro del cual se proporciona la id.
    */
    @DeleteMapping("/admin/deletebook/{id}")
    @PreAuthorize("hasAuthority('book:write')")
    public boolean deleteBook(@PathVariable int id) {

        return updateServices.deleteBook(id);
               
    }
}
