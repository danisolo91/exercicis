package com.ds.joc.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ds.joc.entity.Player;
import com.ds.joc.service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@GetMapping
	public ResponseEntity<?> getAllPlayers() {
		return ResponseEntity.ok(playerService.getAllPlayers());
	}

	@PostMapping
	public ResponseEntity<?> createPlayer(@RequestParam(required = false) String username,
			@RequestParam(required = false) String password) {
		if (username == null || username.isBlank()) {
			username = "Anònim";
		} else {
			if (usernameExists(username)) {
				return ResponseEntity.badRequest().body("El nom d'usuari introduït ja existeix");
			}
			if (password == null || password.isBlank()) {
				return ResponseEntity.badRequest().body("Has d'introduïr una contrasenya");
			}
		}

		Player player = new Player();
		player.setUsername(username);
		player.setPassword(password);
		player.setCreatedAt(new Date());
		playerService.addPlayer(player);

		return ResponseEntity.created(null).body("Jugador creat amb èxit");
	}

	@PutMapping
	public ResponseEntity<?> updatePlayerName(@RequestParam String id, @RequestParam String username) {
		Optional<Player> player = playerService.getPlayerById(id);

		if (player.isEmpty()) {
			return ResponseEntity.badRequest().body("No existeix cap jugador amb l'ID " + id);
		}

		if (username.isBlank()) {
			return ResponseEntity.badRequest().body("Has d'introduïr un nom d'usuari");
		} else {
			if (usernameExists(username)) {
				return ResponseEntity.badRequest().body("El nom d'usuari introduït ja existeix.");
			}
		}

		player.get().setUsername(username);
		playerService.updatePlayer(player.get());

		return ResponseEntity.ok("Nom d'usuari actualitzat");
	}

	/**
	 * Mètode que verifica si un nom d'usuari existeix a la BBDD
	 * 
	 * @param username
	 * @return
	 */
	private boolean usernameExists(String username) {
		return playerService.getPlayersByUsername(username).size() > 0;
	}
}
