package com.ds.joc.entity;

public enum Role {
	PLAYER("Jugador"), ADMIN("Administrador");

	private String name;

	private Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
