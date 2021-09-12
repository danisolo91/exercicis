package com.ds.joc.service;

import java.util.List;
import java.util.Optional;

import com.ds.joc.entity.GameType;
import com.ds.joc.entity.Player;
import com.ds.joc.entity.Ranking;

public interface PlayerService {

	public List<Player> getAllPlayers();

	public List<Player> getPlayersByUsername(String username);

	public Optional<Player> getPlayerById(String playerId);

	public Player addPlayer(Player player);

	public void updatePlayer(Player player);

	public void deletePlayer(String playerId);

	public List<Ranking> getGameAvgSuccessRate();

	public Player getGameLoser(GameType gameType);

	public Player getGameWinner(GameType gameType);

}
