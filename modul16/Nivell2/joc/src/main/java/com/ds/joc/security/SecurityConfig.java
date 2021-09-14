package com.ds.joc.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ds.joc.filter.CustomAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${app.security.jwtSigningKey}")
	private String jwtSigningKey;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers(HttpMethod.POST, "/players").permitAll() // crear nous usuaris
		.anyRequest().authenticated();
		http.addFilterBefore(new CustomAuthorizationFilter(jwtSigningKey), UsernamePasswordAuthenticationFilter.class);
	}
	
}
