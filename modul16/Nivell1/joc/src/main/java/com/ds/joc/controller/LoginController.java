package com.ds.joc.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds.joc.entity.Role;
import com.ds.joc.entity.User;
import com.ds.joc.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Value("${appSecurity.jwtSigningKey}")
	private String jwtSigningKey;

	@PostMapping
	public ResponseEntity<?> login(@RequestParam(required = false) String username,
			@RequestParam(required = false) String password) {
		User user = null;
		String token = null;

		// Si no rebem un nom d'usuari creem un usuari anònim
		if (username == null || username.isBlank()) {
			try {
				user = userService.addUser(new User(Arrays.asList(Role.PLAYER)));
				token = getJwtToken(user);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		// Si rebem un nom d'usuari el busquem a la BD
		else {
			user = userService.getUserByUsername(username);
			if (user != null && password != null) {
				// Comprovem que la contrasenya es valida
				if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
					token = getJwtToken(user);
				}
			}
		}

		if (token == null) {
			return ResponseEntity.badRequest().body("Credencials errònies.");
		}

		user.setToken(token);

		return ResponseEntity.ok(user);
	}

	/** Retorna un token JWT */
	private String getJwtToken(User user) {
		String token = Jwts.builder().setId("Joc de daus").setSubject(user.getId()) // guardem l'ID com a subject
																					// <<<-----
				.claim("roles",
						user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.name()))
								.collect(Collectors.toList()))
				.setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30 minuts
				.signWith(SignatureAlgorithm.HS512, jwtSigningKey.getBytes()).compact();
		return token;
	}
}
