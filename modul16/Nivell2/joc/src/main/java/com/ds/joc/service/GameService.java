package com.ds.joc.service;

import java.util.List;
import java.util.UUID;

import com.ds.joc.entity.Game;
import com.ds.joc.entity.GameType;

public interface GameService {

	public List<Game> getUserGames(UUID userId);

	public Game addGame(Game game);
	
	public void deleteUserGames(UUID userId, GameType gameType);

}
