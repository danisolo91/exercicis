package com.ds.joc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ds.joc.entity.GameType;
import com.ds.joc.entity.User;
import com.ds.joc.entity.Ranking;

public interface UserRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);

	@Aggregation(pipeline = {
			"{ $unwind: '$rankings' }", 
			"{ $group : { _id: '$rankings.gameType', successRate: { $avg: '$rankings.successRate' } } }", 
			"{ $project: { _id: 0, gameType: '$_id', successRate: '$successRate' } }"
	})
	public List<Ranking> findGameAvgSuccessRate();

	@Aggregation(pipeline = {
			"{ $match: { 'rankings.gameType': ?0 } }",
		    "{ $unwind: '$rankings' }", 
		    "{ $sort: { 'rankings.successRate': 1 } }",
		    "{ $limit: 1 }",
		    "{ $project: { username: 1, password: 1, createdAt: 1, rankings: ['$rankings'] } }"
	})
	public User findGameLoser(GameType gameType);
	
	@Aggregation(pipeline = {
			"{ $match: { 'rankings.gameType': ?0 } }",
		    "{ $unwind: '$rankings' }", 
		    "{ $sort: { 'rankings.successRate': -1 } }",
		    "{ $limit: 1 }",
		    "{ $project: { username: 1, password: 1, createdAt: 1, rankings: ['$rankings'] } }"
	})
	public User findGameWinner(GameType gameType);
}
