package com.ds.joc.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Aquest filtre s'executa cada vegada que es rep una petició. S'encarrega de
 * comprovar si la petició porta un token d'acces i l'utilitza per afegir
 * l'usuari al contexte de seguretat de Spring per que consti com a usuari
 * autenticat
 */
public class CustomAuthorizationFilter extends OncePerRequestFilter {

	private String jwtSigningKey;
	
	public CustomAuthorizationFilter(String jwtSigningKey) {
		this.jwtSigningKey = jwtSigningKey;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (tokenExists(request)) {
			try {
				Claims claims = validateToken(request);
				if (claims.get("roles") != null) {
					setUpSpringAuthentication(claims);
				}
				filterChain.doFilter(request, response);
			} catch (Exception e) {
				// Si el token no es valid retornem un forbidden amb un missatge d'error en
				// format JSON
				response.setHeader("error", e.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());
				response.setContentType("application/json");

				Map<String, String> error = new HashMap<>();
				error.put("error_message", e.getMessage());

				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

	/** Verifica si existeix el header Authorization que comença amb "Bearer " */
	private boolean tokenExists(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			return true;
		}
		return false;
	}

	/** Treu el token del header, el decodifica i retorna els claims */
	private Claims validateToken(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION).substring("Bearer ".length());
		return Jwts.parser().setSigningKey(jwtSigningKey.getBytes()).parseClaimsJws(token).getBody();
	}

	/** Creem un objecte que representi l'usuari logat i l'afegim al context */
	private void setUpSpringAuthentication(Claims claims) {
		String userId = claims.getSubject();
		
		List<GrantedAuthority> authorities = Arrays.asList(claims.get("roles"))
				.stream()
				.map(r -> new SimpleGrantedAuthority("ROLE_" + r.toString()))
				.collect(Collectors.toList());

		// El ID de l'usuari será el 'principal' autenticat
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userId, null, authorities);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
