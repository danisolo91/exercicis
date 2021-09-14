package com.ds.joc.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ds.joc.entity.GameType;
import com.ds.joc.entity.User;
import com.ds.joc.entity.Ranking;

public interface UserService {
		
	public User addUser(User user);
	
	public boolean usernameExists(String username);

	public List<User> getAllUsers();

	public User getUserByUsername(String username);

	public Optional<User> getUserById(UUID userId);

	public void updateUser(User user);

	public void deleteUser(UUID userId);

	public List<Ranking> getGameAvgSuccessRate();

	public User getGameLoser(GameType gameType);

	public User getGameWinner(GameType gameType);

}
