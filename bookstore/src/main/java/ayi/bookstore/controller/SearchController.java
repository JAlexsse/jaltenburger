package ayi.bookstore.controller;

import ayi.bookstore.model.*;
import ayi.bookstore.services.OperationServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SearchController {

    @Autowired
    private OperationServices operationServices;

    /* 
    Llama al servicio para buscar un libro atraves de la id proporcionada.
    */
    @GetMapping("/user/searchbook")
    public String returnBook(@RequestParam(name = "id") int id, Model model) {
        try {
            Book book = operationServices.getBookData(id);

            model.addAttribute("book", book);

            return "bookTemplate";
            
        } catch (Exception e) {
            String error = e.getMessage();

            model.addAttribute("message", error);
            return "error";
        }
    }

    /* 
    Llama al servicio para cambiar el nombre del libro del cual se proporciona la id.
    */
    @GetMapping("/admin/modifybook")
    public String modifyBookName(@RequestParam(name = "id") int id,
                                @RequestParam(name = "name") String name, 
                                Model model) {
        try {
            model.addAttribute("message", operationServices.modifyBookName(name, id));
            model.addAttribute("bookafter", operationServices.getBookData(id));
            return "modifybook";
            
        } catch (Exception e) {
            String error = e.getMessage();

            model.addAttribute("message", error);
            return "error";
        }       
    }

    /* 
    Llama al servicio para cambiar el nombre del libro del cual se proporciona la id.
    */
    @GetMapping("/user/authorbooks")
    public String authorBooks(@RequestParam(name = "id") int id, Model model) {
        try {

            Author author = operationServices.getAuthor(id);
            List<Book> books= author.getBook();

            model.addAttribute("author_name", author.getAuthor_name());
            model.addAttribute("books", books);

            return "authorBooks";
            
        } catch (Exception e) {
            String error = e.getMessage();

            model.addAttribute("message", error);
            return "error";
        }       
    }
    
    /* 
    Llama al servicio para cambiar el nombre del libro del cual se proporciona la id.
    */
    @GetMapping("/admin/deletebook")
    public String deleteBook(@RequestParam(name = "id") int id, Model model) {
        try {
            model.addAttribute("message", operationServices.deleteBook(id));
            return "deleteBook";
            
        } catch (Exception e) {
            String error = e.getMessage();

            model.addAttribute("message", error);
            return "error";
        }       
    }
    
}
