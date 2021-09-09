package com.ds.joc.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Guarda tota la informació d'un jugador
 * 
 * @author daniel
 *
 */
@Document(collection = "players")
public class Player {

	@Id
	private String id;
	private String username;
	private String password;
	private Date createdAt;
	private List<Game> games = new ArrayList<Game>();
	private List<Ranking> rankings = new ArrayList<Ranking>();

	public Player() {
	}

	public Player(String id, String username, String password, Date createdAt, List<Game> games,
			List<Ranking> rankings) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.createdAt = createdAt;
		this.games = games;
		this.rankings = rankings;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<Ranking> getRankings() {
		return rankings;
	}

	public void setRankings(List<Ranking> rankings) {
		this.rankings = rankings;
	}

}
