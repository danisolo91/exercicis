package com.ds.joc.service;

import java.util.List;

import com.ds.joc.entity.Game;
import com.ds.joc.entity.GameType;

public interface GameService {

	public List<Game> getPlayerGames(String playerId);

	public Game addGame(Game game);
	
	public void deletePlayerGames(String playerId, GameType gameType);

}
