package com.ds.joc.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Guarda tota la informació d'un usuari
 * 
 * @author daniel
 *
 */
@Document(collection = "users")
public class User {

	@Id
	private String id;
	@Transient
	private String token;
	private String username;
	private String password;
	private boolean anonymous;
	private Date createdAt;
	private List<Ranking> rankings = new ArrayList<Ranking>();
	private List<Role> roles = new ArrayList<Role>();

	/** Constructor per defecte */
	public User() {}
	
	/** Constructor per usuaris registrats */
	public User(String username, String password, List<Role> roles) {
		this.username = username;
		this.password = password;
		this.anonymous = false;
		this.createdAt = new Date();
		this.roles = roles;
	}
	
	/** Constructor per a usuaris anonims */
	public User(List<Role> roles) {
		this.username = "Anònim";
		this.password = UUID.randomUUID().toString();
		this.anonymous = true;
		this.createdAt = new Date();
		this.roles = roles;
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

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/** Afegeix un rol a la llista de rols */
	public void addRole(Role role) {
		roles.add(role);
	}

	/** Actualitza el ranking del usuari a un tipus de joc */
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
		return "User [id=" + id + ", token=" + token + ", username=" + username + ", password=" + password
				+ ", anonymous=" + anonymous + ", createdAt=" + createdAt + ", rankings=" + rankings + ", roles="
				+ roles + "]";
	}

}
