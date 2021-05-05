package ayi.microservicios.servicioitem.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ayi.microservicios.servicioitem.models.Item;
import ayi.microservicios.servicioitem.models.service.ItemService;

@RestController
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @GetMapping("/listar")
    public List<Item> getAll(){
        return itemService.findAll();
    }

    @GetMapping("/detalle/{id}/cantidad/{quantity}")
    public Item details(@PathVariable Long id, @PathVariable Integer quantity){
        return itemService.findById(id, quantity);
    }
}
