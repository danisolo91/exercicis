package com.ds.joc.entity;

/**
 * Enumerable que conté els valors de cada una de les cares d'un dau
 * 
 * @author daniel
 *
 */
public enum DiceValue {
	ONE("Un", 1), TWO("Dos", 2), THREE("Tres", 3), FOUR("Quatre", 4), FIVE("Cinc", 5), SIX("Sis", 6);

	private String name;
	private int value;

	private DiceValue(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
