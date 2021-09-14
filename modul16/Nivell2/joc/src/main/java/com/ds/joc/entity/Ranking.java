package com.ds.joc.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Aquesta classe guarda el percentatge mig d'exit d'un usuari a un tipus de
 * joc determinat
 * 
 * @author daniel
 *
 */
@Document(collection = "rankings")
public class Ranking {

	private GameType gameType;
	private double successRate;

	public Ranking() {
	}

	public Ranking(GameType gameType, double successRate) {
		this.gameType = gameType;
		this.successRate = successRate;
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

	@Override
	public String toString() {
		return "Ranking [gameType=" + gameType + ", successRate=" + successRate + "]";
	}

}
