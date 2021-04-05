package ayi.bookstore.controller;

import ayi.bookstore.exceptions.InformationNotCorrectException;
import ayi.bookstore.model.InitElement;
import ayi.bookstore.services.CreateServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CreateController {

    
    private CreateServices createServices;

    @Autowired
    public CreateController(CreateServices createServices) {
        this.createServices = createServices;
    }

    /* 
    Llama al servicio para crear un nuevo libro, para lo cual se proporciona:
    nombre del libro, el autor y su editorial.

    */
    @PostMapping("/admin/createbook")
    public String createBook(@RequestBody InitElement initElement) {
        
        String result;

        if(initElement.getAuthor_id() > 0 && initElement.getPublishing_id() > 0){
            result = createServices.createBook(
                initElement.getName(), 
                initElement.getAuthor_id(), 
                initElement.getPrice(),
                initElement.getPublishing_id()
                );
        } else if (initElement.getAuthor_name() != "" && initElement.getPublishing_name() != ""){
            result = createServices.createBook(
                initElement.getName(), 
                initElement.getAuthor_name(),
                initElement.getPrice(), 
                initElement.getPublishing_name());
        } else {
            throw new InformationNotCorrectException("The information provided is not correct.");
        }
         

        return result;
    }

    /* 
    Llama al servicio para crear una nueva editorial, para lo cual se proporciona:
    nombre de la editorial, y datos de la direccion: numero, calle y codigo postal.

    */
    @PostMapping("/admin/createpublishing")
    public boolean createPublihsing(@RequestBody InitElement initElement) {

        return createServices.createPublishing(
            initElement.getPublishing_name(), 
            initElement.getNumber(), 
            initElement.getStreet(), 
            initElement.getZipCode()
        ); 
    }

    /* 
    Llama al servicio para crear un nuevo Author, para lo cual se proporciona:
    nombre del autor.

    */
    @PostMapping("/admin/createauthor")
    public String createAuthor(@RequestBody InitElement initElement) {
        
        String response = createServices.createAuthor(initElement.getAuthor_name()); 

        return response;
    }

}
