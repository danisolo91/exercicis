package com.ds.joc.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ds.joc.entity.Game;
import com.ds.joc.entity.GameType;

public interface GameRepository extends MongoRepository<Game, UUID> {
	
	public List<Game> findByUserIdAndType(UUID userId, GameType type);
	
	public void deleteByUserIdAndType(UUID userId, GameType type);
	
}
