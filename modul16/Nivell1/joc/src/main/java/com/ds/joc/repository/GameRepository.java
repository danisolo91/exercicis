package com.ds.joc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ds.joc.entity.Game;
import com.ds.joc.entity.GameType;

public interface GameRepository extends MongoRepository<Game, String> {
	
	public List<Game> findByPlayerId(String playerId);
	
	public void deleteByPlayerIdAndType(String playerId, GameType type);
	
}
