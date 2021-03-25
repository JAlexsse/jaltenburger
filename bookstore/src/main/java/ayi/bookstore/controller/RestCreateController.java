package ayi.bookstore.controller;

import ayi.bookstore.model.InitElement;
import ayi.bookstore.services.RestOperationServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestCreateController {

    @Autowired
    private RestOperationServices restOperationServices;

    /* 
    Llama al servicio para crear un nuevo libro, para lo cual se proporciona:
    nombre del libro, el autor y su editorial.

    POSTMAN body:
        {
        "name": "nombre del libro",
        "author": "id del autor",
        "publishing": "id de la editorial"
        }
    */
    @PostMapping("/createbook")
    public String createBook(@RequestBody InitElement initElement) {
        
        String result;

        if(initElement.getAuthor_id() > 0 && initElement.getPublishing_id() > 0){
            result = restOperationServices.createBook(initElement.getName(), initElement.getAuthor_id(), initElement.getPublishing_id());
        } else if (initElement.getAuthor_name() != "" && initElement.getPublishing_name() != ""){
            result = restOperationServices.createBook(initElement.getName(), initElement.getAuthor_name(), initElement.getPublishing_name());
        } else {
            result = "Failed.";
        }
         

        return result;
    }

    /* 
    Llama al servicio para crear una nueva editorial, para lo cual se proporciona:
    nombre de la editorial.

        POSTMAN body:
        {
        "name":"nombre de la editorial"
        }
    */
    @PostMapping("/createpublishing")
    public String createPublihsing(@RequestBody InitElement initElement) {
        
        String returnedData = restOperationServices.createPublishing(initElement.getName()); 

        return returnedData;
    }

    /* 
    Llama al servicio para crear un nuevo Author, para lo cual se proporciona:
    nombre del autor.

        POSTMAN body:
        {
        "name":"nombre del autor"
        }
    */
    @PostMapping("/createauthor")
    public String createAuthor(@RequestBody InitElement initElement) {
        
        String returnedData = restOperationServices.createAuthor(initElement.getName()); 

        return returnedData;
    }

}
