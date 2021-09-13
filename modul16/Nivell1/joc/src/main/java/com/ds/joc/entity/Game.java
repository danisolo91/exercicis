package com.ds.joc.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Aquesta classe representa la jugada d'un usuari a un determinat tipus de
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
	private List<Integer> diceValues = new ArrayList<Integer>();
	private boolean winner;
	private Date createdAt;
	private String userId;

	public Game() {

	}

	public Game(String id, GameType type, List<Integer> diceValues, boolean winner, Date createdAt, String userId) {
		this.id = id;
		this.type = type;
		this.diceValues = diceValues;
		this.winner = winner;
		this.createdAt = createdAt;
		this.userId = userId;
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

	public List<Integer> getDiceValues() {
		return diceValues;
	}

	public void setDiceValues(List<Integer> diceValues) {
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", type=" + type + ", diceValues=" + diceValues + ", winner=" + winner
				+ ", createdAt=" + createdAt + ", userId=" + userId + "]";
	}

}
