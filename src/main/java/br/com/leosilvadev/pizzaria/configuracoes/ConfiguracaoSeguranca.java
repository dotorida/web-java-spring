package br.com.leosilvadev.pizzaria.configuracoes;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.leosilvadev.pizzaria.modelo.repositorios.UsuarioRepositorio;
import br.com.leosilvadev.pizzaria.modelo.servicos.ServicoAutenticacao;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter{
    
   @Autowired private UsuarioRepositorio usuarioRepositorio;
   @Autowired private ServicoAutenticacao servicoAutenticacao;
   
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(servicoAutenticacao)
        .passwordEncoder(encoder());
            
    }
    
    
   @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/app/pizzaria/**", "/app/ingredientes/**").hasRole("PIZZARIA")
                  .anyRequest().permitAll()
<<<<<<< HEAD
            .and()
                .formLogin()
                    .loginPage("/login.jsp")
                    .loginProcessingUrl("/autenticar")
                    .defaultSuccessUrl("/app/pizzas")
                    .failureUrl("/login.jsp?semacesso=true")
                    .usernameParameter("usuario")
                    .passwordParameter("senha")
            .and()
                .logout()
                    .logoutUrl("/sair")
                    .logoutSuccessUrl("/login.jsp?saiu=true");
=======
            .and().httpBasic();
>>>>>>> parent of 2ad37b1... spring security done and init menu
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        // TODO Auto-generated method stub
        super.configure(web);
    }
    
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    
    
}
