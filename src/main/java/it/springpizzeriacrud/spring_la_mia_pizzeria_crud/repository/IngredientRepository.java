package it.springpizzeriacrud.spring_la_mia_pizzeria_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{

    public Ingredient findByIngredient(String ingredient);

}
