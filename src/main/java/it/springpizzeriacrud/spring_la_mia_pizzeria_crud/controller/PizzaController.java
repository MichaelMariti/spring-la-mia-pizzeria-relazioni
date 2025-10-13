package it.springpizzeriacrud.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.model.Pizza;
import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping("/index")
    public String index(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Pizza> result = null;
        if (keyword == null || keyword.isBlank()) {
            result = repository.findAll();
        } else {
            result = repository.findByNomeContainingIgnoreCase(keyword);
        }
        model.addAttribute("list", result);
        return "/index";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Optional<Pizza> optionalPizza = repository.findById(id);
        if (optionalPizza.isPresent()) {
            model.addAttribute("pizza", optionalPizza.get());
            model.addAttribute("empty", false);
        } else {
            model.addAttribute("empty", true);
        }
        return "/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "/create";
    }

    @PostMapping("/create")
    public String save(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        Optional<Pizza> optPizza = repository.findByNome(formPizza.getNome());
        if (optPizza.isPresent()) {
            bindingResult.addError(new FieldError("pizza", "nome", "Nome già presente"));
        }
        if (bindingResult.hasErrors()) {
            return "/create";
        }
        repository.save(formPizza);
        redirectAttributes.addFlashAttribute("successMessage", "Pizza created successfully");
        return "redirect:/pizzas/index";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Optional<Pizza> optPizza = repository.findById(id);
        Pizza pizza = optPizza.get();
        model.addAttribute("pizza", pizza);
        return "/edit";
    }

    @PostMapping("edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza pizza,
        BindingResult bindingResult) {
        Integer pizzaId = pizza.getId();
        Pizza oldPizza = repository.findById(pizzaId).get();

        if(!oldPizza.getNome().equals(pizza.getNome())){
            bindingResult.addError(new ObjectError("nome", "Non puoi cambiare il nome"));
        }
        if(bindingResult.hasErrors()) {
            return "redirect:/pizzas/edit/" + Integer.toString(pizzaId);
        }

        if (!bindingResult.hasErrors()) repository.save(pizza);

        return "redirect:/pizzas/index";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);        
        return "redirect:/pizzas/index";
    }
    

    // @PostMapping("edit/{id}")
    // public String update(@Valid @ModelAttribute("pizza") Pizza formPizza,
    // BindingResult bindingResult, Model model) {
    // if (formPizza.getId() == null) {
    // // Id mancante, gestisci l'errore
    // model.addAttribute("error", "ID mancante");
    // return "/edit"; // oppure redirect a index
    // }

    // Pizza oldPizza = repository.findById(formPizza.getId()).orElse(null);
    // if (oldPizza == null) {
    // // Pizza non trovata nel DB
    // return "redirect:/pizzas/index";
    // }

    // if (!oldPizza.getNome().equals(formPizza.getNome())) {
    // bindingResult.addError(new FieldError("pizza", "nome", "Non puoi modificare il nome!"));
    // }

    // if (bindingResult.hasErrors()) {
    // return "/edit";
    // }

    // repository.save(formPizza);
    // return "redirect:/pizzas/index";
    // }

}
