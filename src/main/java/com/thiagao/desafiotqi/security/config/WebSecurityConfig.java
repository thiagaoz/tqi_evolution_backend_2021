package com.thiagao.desafiotqi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.thiagao.desafiotqi.clientes.ClienteService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private final ClienteService clienteService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public WebSecurityConfig(ClienteService clienteService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.clienteService = clienteService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
		.csrf().disable()
	    .anonymous()
	    .and()
		    .authorizeRequests()
		    .antMatchers("/index.html","/cadastro/**").permitAll()
		    .antMatchers("/resources/**").permitAll()
		    .antMatchers("/cliente/**").authenticated() 
		    .anyRequest()
			.permitAll().and()
			.formLogin()
				.defaultSuccessUrl("/cliente.html", true)
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index.html")
				;
        ;  
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		provider.setUserDetailsService(clienteService);
		return provider;
	}
	
}
