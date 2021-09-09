package com.ds.joc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Aquesta classe guarda el percentatge mig d'exit d'un jugador a un tipus de
 * joc determinat
 * 
 * @author daniel
 *
 */
@Document(collection = "rankings")
public class Ranking {

	@Id
	private String id;
	private GameType gameType;
	private double successRate;
	private String playerId;

	public Ranking(String id, GameType gameType, double successRate, String playerId) {
		this.id = id;
		this.gameType = gameType;
		this.successRate = successRate;
		this.playerId = playerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public double getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(double successRate) {
		this.successRate = successRate;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	@Override
	public String toString() {
		return "Ranking [id=" + id + ", gameType=" + gameType + ", successRate=" + successRate + ", playerId="
				+ playerId + "]";
	}

}
