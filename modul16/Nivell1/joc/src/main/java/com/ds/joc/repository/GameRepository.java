package com.ds.joc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ds.joc.entity.Game;
import com.ds.joc.entity.GameType;

public interface GameRepository extends MongoRepository<Game, String> {
	
	public List<Game> findByUserId(String userId);
	
	public void deleteByUserIdAndType(String userId, GameType type);
	
}
