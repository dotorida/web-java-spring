package br.com.leosilvadev.pizzaria.configuracoes;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@EnableWebMvc
@Controller
@ComponentScan(basePackages="br.com.leosilvadev.pizzaria")
public class ConfiguracaoWeb extends WebMvcConfigurerAdapter{
    
   @Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		registry.viewResolver(viewResolver);
}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");
        
        registry.addInterceptor(localeInterceptor);
    }
     
     @Autowired
    Environment env;
    	
    @Bean
    public LocaleResolver sessionLocaleResolver(){
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        //resolver.setDefaultLocale(new Locale("pt_PT"));
        resolver.setDefaultLocale(new Locale(env.getProperty("system.default.language")));
        System.out.println("LocaleResolver "+resolver.getDefaultTimeZone());
        return resolver;
    }
    
    @Bean
    public MessageSource messageSource(){
         System.out.println("MessageSource");
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/WEB-INF/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}