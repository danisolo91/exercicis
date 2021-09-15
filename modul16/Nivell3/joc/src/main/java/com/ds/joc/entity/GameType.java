package com.ds.joc.entity;

/**
 * Enumerable que conté els tipus de jocs disponibles i les seves
 * característiques
 * 
 * @author daniel
 *
 */
public enum GameType {
	GAMEONE("Suma set", 7, 2, "Per guanyar, la suma dels dos daus ha de donar set."),
	GAMETWO("Parells", 0, 2, "Per guanyar, la suma dels dos daus ha de donar un nombre parell."),
	GAMETHREE("Blackjack", 21, 3, "Per guanyar, al multiplicar el valor dels tres daus no ha de donar més de 21.");
	
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

	/**
	 * Verifica si existeix un tipus de joc que s'anomeni igual que el tipus rebut
	 * per paràmetre.
	 */
	public static boolean contains(String type) {
		for (GameType g : GameType.values()) {
			if (g.name().equals(type)) {
				return true;
			}
		}
		return false;
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
