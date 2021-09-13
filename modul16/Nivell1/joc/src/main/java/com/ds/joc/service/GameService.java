package com.ds.joc.service;

import java.util.List;

import com.ds.joc.entity.Game;
import com.ds.joc.entity.GameType;

public interface GameService {

	public List<Game> getUserGames(String userId);

	public Game addGame(Game game);
	
	public void deleteUserGames(String userId, GameType gameType);

}
