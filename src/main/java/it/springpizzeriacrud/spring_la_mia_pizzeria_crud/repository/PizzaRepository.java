package it.springpizzeriacrud.spring_la_mia_pizzeria_crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.springpizzeriacrud.spring_la_mia_pizzeria_crud.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

    List<Pizza> findByNomeContainingIgnoreCase(String nome);

    public Optional<Pizza> findByNome(String nome);

}
