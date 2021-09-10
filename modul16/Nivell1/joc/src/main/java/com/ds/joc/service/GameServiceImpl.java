package com.ds.joc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.joc.entity.Game;
import com.ds.joc.entity.GameType;
import com.ds.joc.repository.GameRepository;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;
	
	@Override
	public List<Game> getPlayerGames(String playerId) {
		return gameRepository.findByPlayerId(playerId);
	}

	@Override
	public Game addGame(Game game) {
		return gameRepository.insert(game);
	}

	@Override
	public void deletePlayerGames(String playerId, GameType gameType) {
		gameRepository.deleteByPlayerIdAndType(playerId, gameType);
	}

}
