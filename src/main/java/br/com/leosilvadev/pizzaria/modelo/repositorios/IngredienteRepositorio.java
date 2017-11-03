package br.com.leosilvadev.pizzaria.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.leosilvadev.pizzaria.modelo.entidades.Ingrediente;

@Repository
public interface  IngredienteRepositorio extends CrudRepository<Ingrediente, Long>{
    
    
}