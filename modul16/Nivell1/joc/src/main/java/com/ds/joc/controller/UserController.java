package com.ds.joc.controller;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds.joc.entity.GameType;
import com.ds.joc.entity.User;
import com.ds.joc.entity.Ranking;
import com.ds.joc.entity.Role;
import com.ds.joc.service.UserService;

@RestController
@RequestMapping("/players")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private GameController gameController;

	@Autowired
	private RankingController rankingController;

	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@PostMapping
	public ResponseEntity<?> addUser(@RequestParam(required = false) String username,
			@RequestParam(required = false) String password) {
		if (username == null || username.isBlank()) {
			return ResponseEntity.badRequest()
					.body("Per jugar com un usuari anònim, has de fer POST a /login sense cap credencial.");
		}
		
		if (password == null || password.isBlank()) {
			return ResponseEntity.badRequest().body("Has d'introduïr una contrasenya.");
		}
		
		if (userService.usernameExists(username)) {
			return ResponseEntity.badRequest().body("El nom d'usuari introduït ja existeix.");
		}
		
		userService.addUser(new User(username, password, Arrays.asList(Role.PLAYER)));

		return ResponseEntity.created(null).body("Usuari creat amb èxit");
	}

	@PutMapping
	public ResponseEntity<?> updateUser(@RequestParam String id, @RequestParam String username) {
		Optional<User> user = userService.getUserById(id);

		if (user.isEmpty()) {
			return ResponseEntity.badRequest().body("No existeix cap usuari amb l'ID " + id);
		}
		
		if (!isAuthenticated(user.get())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		if (user.get().isAnonymous()) {
			return ResponseEntity.badRequest().body("Els usuaris anònims no poden canviar el seu nom d'usuari.");
		}

		if (username.isBlank()) {
			return ResponseEntity.badRequest().body("Has d'introduïr un nom d'usuari");
		} else {
			if (userService.usernameExists(username)) {
				return ResponseEntity.badRequest().body("El nom d'usuari introduït ja existeix.");
			}
		}

		user.get().setUsername(username);
		userService.updateUser(user.get());

		return ResponseEntity.ok("Nom d'usuari actualitzat");
	}

	@PostMapping("/{id}/games")
	public ResponseEntity<?> playGame(@PathVariable String id) {
		GameType gameType = GameType.GAMEONE;
		Optional<User> user = userService.getUserById(id);

		if (user.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		if (!isAuthenticated(user.get())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		// Executem un joc i afegim el resultat a la llista de jocs
		gameController.play(user.get().getId(), gameType);

		// Obtenim i actualitzem el ranking del usuari a aquest tipus de joc
		Ranking ranking = rankingController.getRanking(gameController.getUserGames(user.get().getId()), gameType);
		user.get().updateRanking(ranking, gameType);

		userService.updateUser(user.get());

		return ResponseEntity.created(null).body("Resultats del joc guardats amb èxit");
	}

	@GetMapping("/{id}/games")
	public ResponseEntity<?> getUserGames(@PathVariable String id) {
		Optional<User> user = userService.getUserById(id);

		if (user.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		if (!isAuthenticated(user.get())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		return ResponseEntity.ok(gameController.getUserGames(user.get().getId()));
	}

	@DeleteMapping("/{id}/games")
	public ResponseEntity<?> deleteUserGames(@PathVariable String id) {
		GameType gameType = GameType.GAMEONE;
		Optional<User> user = userService.getUserById(id);

		if (user.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		if (!isAuthenticated(user.get())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		gameController.deleteUserGames(user.get().getId(), gameType);
		user.get().deleteRanking(gameType);
		userService.updateUser(user.get());

		return ResponseEntity.ok("S'han eliminat totes les jugades de " + user.get().getId());
	}

	/**
	 * Retorna el percentatge mig d'èxit de tots els usuaris a cadascún dels tipus
	 * de jocs
	 */
	@GetMapping("/ranking")
	public ResponseEntity<?> getRanking() {
		return ResponseEntity.ok(userService.getGameAvgSuccessRate());
	}

	/** Retorna el usuari amb pitjor percentatge d'èxit a un determinat joc */
	@GetMapping("/ranking/loser")
	public ResponseEntity<?> getLoser() {
		GameType gameType = GameType.GAMEONE;
		return ResponseEntity.ok(userService.getGameLoser(gameType));
	}

	/** Retorna el usuari amb millor percentatge d'èxit a un determinat joc */
	@GetMapping("/ranking/winner")
	public ResponseEntity<?> getWinner() {
		GameType gameType = GameType.GAMEONE;
		return ResponseEntity.ok(userService.getGameWinner(gameType));
	}

	/** Mètode que verifica si un usuari està autenticat */
	private boolean isAuthenticated(User user) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		
		if (currentUsername.equals(user.getId())) {
			return true;
		}

		return false;
	}
}
