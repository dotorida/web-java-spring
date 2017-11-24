package br.com.leosilvadev.pizzaria.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.leosilvadev.pizzaria.modelo.entidades.Usuario;

public interface UsuarioRepositorio  extends CrudRepository<Usuario, Long>{
    
    public Usuario findOneByLogin(String login);
        
    
}
