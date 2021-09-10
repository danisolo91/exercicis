package com.ds.joc.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
	private List<Ranking> rankings = new ArrayList<Ranking>();

	public Player() {
	}

	public Player(String id, String username, String password, Date createdAt, List<Ranking> rankings) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.createdAt = createdAt;
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

	public List<Ranking> getRankings() {
		return rankings;
	}

	public void setRankings(List<Ranking> rankings) {
		this.rankings = rankings;
	}

	/** Actualitza el ranking del jugador a un tipus de joc */
	public void updateRanking(Ranking ranking, GameType gameType) {
		for (int i = 0; i < rankings.size(); i++) {
			if (rankings.get(i).getGameType().equals(gameType)) {
				rankings.set(i, ranking);
				return;
			}
		}
		rankings.add(ranking);
	}

	/** Elimina el ranking d'un determinat tipus de joc */
	public void deleteRanking(GameType gameType) {
		Iterator<Ranking> it = rankings.iterator();
		while (it.hasNext()) {
			Ranking ranking = it.next();
			if (ranking.getGameType().equals(gameType)) {
				it.remove();
				break;
			}
		}
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", username=" + username + ", password=" + password + ", createdAt=" + createdAt
				+ ", rankings=" + rankings + "]";
	}

}
