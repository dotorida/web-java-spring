package br.com.leosilvadev.pizzaria.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.leosilvadev.pizzaria.modelo.entidades.Pizza;

@Repository
public interface PizzaRepositorio extends CrudRepository<Pizza, Long>{
    
    
}