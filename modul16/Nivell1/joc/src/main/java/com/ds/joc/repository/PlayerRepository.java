package com.ds.joc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ds.joc.entity.Player;

public interface PlayerRepository extends MongoRepository<Player, String> {

	public List<Player> findByUsername(String username);

}
