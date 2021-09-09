package com.ds.joc.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Aquesta classe representa la jugada d'un jugador a un determinat tipus de
 * joc. Guarda els valors dels daus així com el resultat (si ha sigut guanyador
 * o no)
 * 
 * @author daniel
 *
 */
@Document(collection = "games")
public class Game {

	@Id
	private String id;
	private GameType type;
	private List<DiceValue> diceValues = new ArrayList<DiceValue>();
	private boolean winner;
	private Date createdAt;
	private String playerId;

	public Game(String id, GameType type, List<DiceValue> diceValues, boolean winner, Date createdAt, String playerId) {
		this.id = id;
		this.type = type;
		this.diceValues = diceValues;
		this.winner = winner;
		this.createdAt = createdAt;
		this.playerId = playerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GameType getType() {
		return type;
	}

	public void setType(GameType type) {
		this.type = type;
	}

	public List<DiceValue> getDiceValues() {
		return diceValues;
	}

	public void setDiceValues(List<DiceValue> diceValues) {
		this.diceValues = diceValues;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", type=" + type + ", diceValues=" + diceValues + ", winner=" + winner
				+ ", createdAt=" + createdAt + ", playerId=" + playerId + "]";
	}

}
