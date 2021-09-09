package com.ds.joc.entity;

/**
 * Enumerable que conté els tipus de jocs disponibles i les seves característiques
 * @author daniel
 *
 */
public enum GameType {
	GAMEONE("SumaSet", 7, 2, "Per guanyar, la suma dels dos daus ha de donar set.");

	private String name;
	private int pointsToWin;
	private int numberOfDices;
	private String description;

	private GameType(String name, int pointsToWin, int numberOfDices, String description) {
		this.name = name;
		this.pointsToWin = pointsToWin;
		this.numberOfDices = numberOfDices;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPointsToWin() {
		return pointsToWin;
	}

	public void setPointsToWin(int pointsToWin) {
		this.pointsToWin = pointsToWin;
	}

	public int getNumberOfDices() {
		return numberOfDices;
	}

	public void setNumberOfDices(int numberOfDices) {
		this.numberOfDices = numberOfDices;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
