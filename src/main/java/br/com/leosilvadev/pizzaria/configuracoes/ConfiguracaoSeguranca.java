package br.com.leosilvadev.pizzaria.configuracoes;


<<<<<<< HEAD
=======
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> parent of 99d1490... erro 404
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
<<<<<<< HEAD
=======
>>>>>>> parent of 1f17152... trying to encode password on database
=======
    
    @Test
    public void ola(){
        System.out.println("ola");
    }
    
>>>>>>> parent of 99d1490... erro 404
}
