package br.com.leosilvadev.pizzaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.leosilvadev.pizzaria.excecoes.IngredienteInvalidException;
import br.com.leosilvadev.pizzaria.modelo.entidades.Ingrediente;
import br.com.leosilvadev.pizzaria.modelo.enumeracoes.CategoriaDeIngrediente;
import br.com.leosilvadev.pizzaria.modelo.repositorios.IngredienteRepositorio;


@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {
    
    
    @Autowired private IngredienteRepositorio ingredienteRepositorio;
       
    @RequestMapping(method=RequestMethod.GET)
    public String listarIngredientes(Model model){
        Iterable<Ingrediente> ingrendientes = ingredienteRepositorio.findAll();     
        model.addAttribute("titulo", "first title");
        model.addAttribute("ingredientes", ingrendientes);
        model.addAttribute("categorias",CategoriaDeIngrediente.values());
                
        return "ingrediente/listagem";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String salvarIngrediente(@Valid @ModelAttribute Ingrediente ingrediente, 
    BindingResult bindingResult, Model model){     
       
       if(bindingResult.hasFieldErrors()){
           throw new IngredienteInvalidException();
                             
        }else{
            ingredienteRepositorio.save(ingrediente);
            
        }
        model.addAttribute("ingredientes", ingredienteRepositorio.findAll());
        model.addAttribute("categorias",CategoriaDeIngrediente.values());
        
        return "ingrediente/tabela-ingrediente";
    }
    
    
    @RequestMapping(method=RequestMethod.GET, value="/{id}")
    @ResponseBody
    public Ingrediente buscarIngrendiente(@PathVariable Long id){
        Ingrediente ingrediente = ingredienteRepositorio.findOne(id);
        return ingrediente;
    } 
    
    
    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public ResponseEntity<String> deletarIngrediente(@PathVariable Long id){
       System.out.println("no metodo deletarIngrediente "+id); 
       try{
            ingredienteRepositorio.delete(id);  
            System.out.println("apagou com sucesso");
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
    
   
}