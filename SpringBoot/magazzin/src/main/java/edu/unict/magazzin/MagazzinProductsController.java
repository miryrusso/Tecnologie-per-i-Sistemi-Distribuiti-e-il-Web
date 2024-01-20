package edu.unict.magazzin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/products")
public class MagazzinProductsController {
    private final MagazzinProductsRepository repository;


    public MagazzinProductsController(MagazzinProductsRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getAvailableProducts(Model model){
        model.addAttribute("products",repository.findByQuantityGreaterThan(0));
        return ("products");
    }

    @GetMapping(path="/searchByName")
    public String getProductFiltered(Model model,@RequestParam String keyword) {
        model.addAttribute("products", repository.findByNameContaining(keyword));
        return "products";
    }
    
}
