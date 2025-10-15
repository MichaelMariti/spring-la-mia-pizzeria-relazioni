package it.springpizzeriacrud.spring_la_mia_pizzeria_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.model.Ingredient;
import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository repository;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("list", repository.findAll());
        model.addAttribute("ingredientObj", new Ingredient());
        return "ingredients/index";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("ingredientObj") Ingredient ingredient, BindingResult bindingResult,
            Model model) {
        
        // Ingredient ing = repository.findByIngredient(ingredient.getIngredient());
        // if (ing == null) {
        // // se NON esiste un ingrediente con quel nome
        // } else {
        // // se esiste
        // bindingResult.addError(new ObjectError("ingredient", "The ingredient already
        // exists"));
        // }

        if (bindingResult.hasErrors()) {
        model.addAttribute("list", repository.findAll());
        model.addAttribute("ingredientObj", new Ingredient());
        return "ingredients/index";
        }
        repository.save(ingredient);
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        repository.deleteById(id);

        return "redirect:/ingredients";
    }

}
