 package br.com.leosilvadev.pizzaria.modelo.entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    private String login;

    private String senha;
    
    @ManyToMany
    private Set<Permissao> permissoes;
    
    @Override
    public Collection<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> autorizacoes = new ArrayList<GrantedAuthority>();
        for (Permissao permissao: getPermissoes()){
            autorizacoes.add(new SimpleGrantedAuthority(permissao.getNome()));
        }
        return autorizacoes;
    }

    @Override
    public String getPassword(){
        return senha;
    }

    public String getUsername(){
        return login;
    }

    public boolean isAccountNonExpired(){
        return true;
    }

    public boolean isAccountNonLocked(){
        return true;
    }

    public boolean isCredentialsNonExpired(){
        return true;
    }

    public boolean isEnabled(){
        return true;
    }



    public Long getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getSenha() {
        return this.senha;

    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
     public Set<Permissao> getPermissoes() {
        return this.permissoes;

    }

    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
    
   
    


}
