package com.ds.joc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.ds.joc.entity.Game;
import com.ds.joc.entity.GameType;
import com.ds.joc.service.GameService;

@Controller
public class GameController {

	@Autowired
	private GameService gameService;

	/** Retorna totes les jugades d'un jugador */
	public List<Game> getUserGames(UUID userId) {
		return gameService.getUserGames(userId);
	}

	/** Elimina totes les jugades d'un jugador a un tipus de joc */
	public void deleteUserGames(UUID userId, GameType gameType) {
		gameService.deleteUserGames(userId, gameType);
	}

	/**
	 * Mètode que guarda la jugada d'un usuari a un tipus de joc.
	 * 
	 * @param userId
	 * @param gameType
	 * @return
	 */
	public Game play(UUID userId, GameType gameType) {
		Game game = new Game();
		game.setType(gameType);
		game.setDiceValues(getRandomDiceValues(gameType));
		game.setWinner(hasWon(gameType, game.getDiceValues()));
		game.setCreatedAt(new Date());
		game.setUserId(userId);
		return gameService.addGame(game);
	}
	
	/** Retorna les opcions de GameType en format String */
	public String gameTypesToString() {
		String result = "";
		for(int i = 0; i < GameType.values().length; i++) {
			result += GameType.values()[i] + ", ";
		}
		result = result.substring(0, result.length() - 2);
		return result;
	}

	/**
	 * Mètode que comprova si s'ha guanyat el joc segons els valors dels daus.
	 * 
	 * @param gameType
	 * @param diceValues
	 * @return
	 */
	private boolean hasWon(GameType gameType, List<Integer> diceValues) {
		boolean result = false;

		switch (gameType) {
		case GAMEONE:
			int sum1 = diceValues.stream().reduce(0, (a, b) -> a + b);
			if (sum1 == gameType.getPointsToWin()) {
				result = true;
			}
			break;
		case GAMETWO:
			int sum2 = diceValues.stream().reduce(0, (a, b) -> a + b);
			if (sum2 % 2 == gameType.getPointsToWin()) {
				result = true;
			}
			break;
		case GAMETHREE:
			int mul1 = diceValues.stream().reduce(1, (a, b) -> a * b);
			if (mul1 <= gameType.getPointsToWin()) {
				result = true;
			}
			break;
		default:
			System.out.println("GameController.hasWon() switch error");
		}

		return result;
	}

	/**
	 * Mètode que retorna una llista de valors de dau aleatoris. El nombre de daus
	 * es determina segons el tipus de joc.
	 * 
	 * @param gameType
	 * @return
	 */
	private List<Integer> getRandomDiceValues(GameType gameType) {
		List<Integer> diceValues = new ArrayList<Integer>();
		for (int i = 0; i < gameType.getNumberOfDices(); i++) {
			diceValues.add(getRandomNumber(1, 7));
		}
		return diceValues;
	}

	/**
	 * Mètode que retorna un nombre enter aleatori entre un valor mínim i un valor
	 * màxim rebut per paràmetre (el valor màxim no está inclós com a possible
	 * resultat).
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	private int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
