package com.ds.joc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.joc.entity.GameType;
import com.ds.joc.entity.Player;
import com.ds.joc.entity.Ranking;
import com.ds.joc.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	@Override
	public List<Player> getPlayersByUsername(String username) {
		return playerRepository.findByUsername(username);
	}

	@Override
	public Optional<Player> getPlayerById(String playerId) {
		return playerRepository.findById(playerId);
	}

	@Override
	public Player addPlayer(Player player) {
		return playerRepository.insert(player);
	}

	@Override
	public void updatePlayer(Player player) {
		playerRepository.save(player);
	}

	@Override
	public void deletePlayer(String playerId) {
		playerRepository.deleteById(playerId);
	}

	@Override
	public List<Ranking> getGameAvgSuccessRate() {
		return playerRepository.findGameAvgSuccessRate();
	}

	@Override
	public Player getGameLoser(GameType gameType) {
		return playerRepository.findGameLoser(gameType);
	}

	@Override
	public Player getGameWinner(GameType gameType) {
		return playerRepository.findGameWinner(gameType);
	}

}
