package br.com.leosilvadev.pizzaria.configuracoes;

<<<<<<< HEAD
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
=======
>>>>>>> parent of 1f17152... trying to encode password on database
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter{
    
   
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("PIZZARIA");
    }
    
    
   @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/app/pizzaria/**", "/app/ingredientes/**").hasRole("PIZZARIA")
                  .anyRequest().permitAll()
<<<<<<< HEAD
<<<<<<< HEAD
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
<<<<<<< HEAD
=======
            .and().httpBasic();
>>>>>>> parent of 2ad37b1... spring security done and init menu
=======
            .and().httpBasic();
>>>>>>> parent of 2ad37b1... spring security done and init menu
=======
            .and().httpBasic();
>>>>>>> parent of 2ad37b1... spring security done and init menu
=======
                        
                    
>>>>>>> parent of 1f17152... trying to encode password on database
    }
    
    
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        // TODO Auto-generated method stub
        super.configure(web);
    }
<<<<<<< HEAD
    
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    
    
=======
>>>>>>> parent of 1f17152... trying to encode password on database
}
