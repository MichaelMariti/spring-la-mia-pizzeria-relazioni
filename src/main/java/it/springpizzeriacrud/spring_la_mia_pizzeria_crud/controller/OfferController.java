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

import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.model.Offer;
import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import jakarta.validation.Valid;




@Controller
@RequestMapping("/offers")
public class OfferController {


    @Autowired
    private OfferRepository repository;

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("offer") Offer offer, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "offers/edit";
        }
        repository.save(offer);
        return "redirect:/pizzas/show/" + offer.getPizza().getId();
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Offer offer = repository.findById(id).get();
        model.addAttribute("editMode", true);
        model.addAttribute("offer", offer);
        return "offers/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("offer") Offer offer, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("editMode", true);
            return "/offers/edit";
        }
        repository.save(offer);
        return "redirect:/pizzas/show/" + offer.getPizza().getId();
    }
    
    
}
