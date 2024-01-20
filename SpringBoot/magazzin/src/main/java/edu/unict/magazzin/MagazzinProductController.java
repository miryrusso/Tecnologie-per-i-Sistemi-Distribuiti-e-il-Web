package edu.unict.magazzin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/product")
public class MagazzinProductController {

    private final MagazzinProductsRepository repository;
  

    public MagazzinProductController(MagazzinProductsRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping
    public String getProductTemplate(Model model) {
        model.addAttribute("product", new MagazzinProductsModel());
        return "product";
    }
    
    @GetMapping(path="/{id}")
    public String getProductbyId(Model model,@PathVariable Long id) {
        model.addAttribute("product", repository.getReferenceById(id));
        return "product";
    }

    @GetMapping(path="/delete/{id}")
    public String deleteProduct(Model model,@PathVariable Long id) {
        MagazzinProductsModel product = repository.getReferenceById(id);
        repository.delete(product);
        return "redirect:/products";
    }

    @PostMapping
    public String createProduct(@ModelAttribute MagazzinProductsModel p, Model model) {
        repository.save(p);
        return "redirect:/products";
    }
    
}
