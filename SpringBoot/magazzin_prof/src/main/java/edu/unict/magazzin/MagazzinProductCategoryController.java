package edu.unict.magazzin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/category")
public class MagazzinProductCategoryController {
    private final MagazzinProductCategoryRepository repository;

    public MagazzinProductCategoryController(MagazzinProductCategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String templateCategory(Model model){
        model.addAttribute("category",new MagazzinProductCategoryModel());
        return ("category");
    }
      
    @GetMapping(path="/{id}")
    public String getCategory(Model model,@PathVariable Long id) {
        model.addAttribute("category", repository.getReferenceById(id));
        return "category";
    }

    @PostMapping
    public String createCategory(@ModelAttribute MagazzinProductCategoryModel p, Model model) {
        repository.save(p);
        return "redirect:/products"; // Todo
    }
    
}
