package com.ds.joc.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

	@Value("${app.security.jwtSigningKey}")
	private String jwtSigningKey;

	@Value("${app.config.allowAnonymousUsers}")
	private boolean allowAnonymousUsers;

	@PostMapping
	public ResponseEntity<?> login(@RequestParam(required = false) String username,
			@RequestParam(required = false) String password) {
		User user = null;
		String token = null;

		// Si no rebem un nom d'usuari creem un usuari anònim
		if (username == null || username.isBlank()) {
			if (allowAnonymousUsers) {
				user = userService.addUser(new User(Arrays.asList(Role.PLAYER)));
				token = getJwtToken(user);
			} else {
				return ResponseEntity.badRequest().body(mapMessage("No es permet entrar com a usuari anònim."));
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
			return ResponseEntity.badRequest().body(mapMessage("Credencials errònies."));
		}

		user.setToken(token);

		return ResponseEntity.ok(user);
	}

	/** Retorna un token JWT */
	private String getJwtToken(User user) {
		String token = Jwts.builder().setId("Joc de daus").setSubject(user.getId().toString()) // guardem l'ID com a
																								// subject <<<-----
				.claim("roles",
						user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.name()))
								.collect(Collectors.toList()))
				.setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30 minuts
				.signWith(SignatureAlgorithm.HS512, jwtSigningKey.getBytes()).compact();
		return token;
	}

	/**
	 * Retorna en un Map un missatge rebut per paràmetre. Es util per poder retornar
	 * amb ResponseEntity missatges en format JSON.
	 */
	private Map<String, String> mapMessage(String message) {
		Map<String, String> result = new HashMap<>();
		result.put("message", message);
		return result;
	}
}
