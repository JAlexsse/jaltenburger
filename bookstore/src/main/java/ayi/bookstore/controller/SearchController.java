package ayi.bookstore.controller;

import ayi.bookstore.entity.*;
import ayi.bookstore.services.SearchServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SearchController {

    @Autowired
    private SearchServices searchServices;

    /* 
    Llama al servicio para buscar un libro atraves de la id proporcionada.
    */
    @GetMapping("/user/searchbook/{id}")
    @PreAuthorize("hasAuthority('book:read')")
    public String returnBook(@PathVariable(name = "id") int id, Model model) {
        try {
            Book book = searchServices.getBookData(id);

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
    @GetMapping("/user/authorbooks/{id}")
    @PreAuthorize("hasAuthority('author:read')")
    public String authorBooks(@PathVariable(name = "id") int id, Model model) {
        try {

            Author author = searchServices.getAuthor(id);
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
    Llama al servicio para buscar un libro atraves de la id proporcionada
    y devuelve: el libro y su precio calculado con impuestos.
    */
    @GetMapping("/admin/bookprices")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String bookPrices(@RequestParam(name = "id") int id, Model model) {
        try {
            Book book = searchServices.getBookData(id);

            model.addAttribute("book", book);
            model.addAttribute("priceTaxed", book.calculatePrice());

            return "bookPrices";
            
        } catch (Exception e) {
            String error = e.getMessage();

            model.addAttribute("message", error);
            return "error";
        }
    }

    
}
