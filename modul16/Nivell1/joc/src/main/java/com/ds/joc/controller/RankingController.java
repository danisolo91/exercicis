package com.ds.joc.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;

import com.ds.joc.entity.Game;
import com.ds.joc.entity.GameType;
import com.ds.joc.entity.Ranking;

@Controller
public class RankingController {

	/**
	 * Retorna el percentange mig d'èxit d'una llista de jocs d'un mateix tipus
	 */
	public Ranking getRanking(List<Game> games, GameType gameType) {
		List<Game> gamesTypeFiltered = games.stream().filter(g -> g.getType().equals(gameType))
				.collect(Collectors.toList());

		double winningGames = (double) gamesTypeFiltered.stream().filter(g -> g.isWinner()).count();
		double gamesSize = (double) gamesTypeFiltered.size();
		double successRate = Math.round(((winningGames / gamesSize) * 100) * 100.0) / 100.0;

		Ranking ranking = new Ranking();
		ranking.setGameType(gameType);
		ranking.setSuccessRate(successRate);

		return ranking;
	}
}
