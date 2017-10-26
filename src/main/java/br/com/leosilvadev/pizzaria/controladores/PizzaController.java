package br.com.leosilvadev.pizzaria.controladores;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.leosilvadev.pizzaria.excecoes.IngredienteInvalidException;
import br.com.leosilvadev.pizzaria.modelo.entidades.Ingrediente;
import br.com.leosilvadev.pizzaria.modelo.entidades.Pizza;
import br.com.leosilvadev.pizzaria.modelo.enumeracoes.CategoriaDePizza;
import br.com.leosilvadev.pizzaria.modelo.repositorios.IngredienteRepositorio;
import br.com.leosilvadev.pizzaria.modelo.repositorios.PizzaRepositorio;
import br.com.leosilvadev.pizzaria.propertyEditors.IngredientePropertyEditor;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    
    @Autowired private PizzaRepositorio pizzaRepositorio;
    @Autowired private IngredientePropertyEditor ingredientePropertyEditor;
    @Autowired private IngredienteRepositorio ingredienteRepositorio;  
    
    @RequestMapping("/quantas")
    @ResponseBody
    public String quantasPizzas(){
        return "Actualmente ha "+pizzaRepositorio.count()+" cadastrados";
    }
    
    
    @RequestMapping(method=RequestMethod.POST)
    public String salvarPizza(@Valid @ModelAttribute Pizza pizza, 
    BindingResult bindingResult, Model model){     
       
       
            /*List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
            }*/
       
       
       if(bindingResult.hasFieldErrors()){
           throw new IngredienteInvalidException();
                             
        }else{
            pizzaRepositorio.save(pizza);
        }
        model.addAttribute("pizzas", pizzaRepositorio.findAll());
        model.addAttribute("categorias",CategoriaDePizza.values());
        
        return "pizza/tabela-pizza";
    }
    
    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public  ResponseEntity<String> deletarPizza(@PathVariable Long id){
        try{
            pizzaRepositorio.delete(id);
            return new  ResponseEntity<String>(HttpStatus.OK);
        }catch(Exception ex){
            return new  ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String listarPizzas(Model model){
        model.addAttribute("pizzas", pizzaRepositorio.findAll());
        model.addAttribute("categorias", CategoriaDePizza.values()); 
        model.addAttribute("ingredientes", ingredienteRepositorio.findAll());
        return "pizza/listagem";
    }
    
    
    @RequestMapping(method=RequestMethod.GET, value="/{id}")
    public ResponseEntity<Pizza>  buscarPizza(@PathVariable long id){
        Pizza pizza = pizzaRepositorio.findOne(id);
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }
    
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(Ingrediente.class, ingredientePropertyEditor);
    }
    
    
 
    
}
