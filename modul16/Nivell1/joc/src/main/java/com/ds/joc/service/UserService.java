package com.ds.joc.service;

import java.util.List;
import java.util.Optional;

import com.ds.joc.entity.GameType;
import com.ds.joc.entity.User;
import com.ds.joc.entity.Ranking;
import com.ds.joc.entity.Role;

public interface UserService {
	
	public User createUser(String username, String password, List<Role> roles) throws Exception;
	
	public User addUser(User user);
	
	public boolean usernameExists(String username);

	public List<User> getAllUsers();

	public User getUserByUsername(String username);

	public Optional<User> getUserById(String userId);

	public void updateUser(User user);

	public void deleteUser(String userId);

	public List<Ranking> getGameAvgSuccessRate();

	public User getGameLoser(GameType gameType);

	public User getGameWinner(GameType gameType);

}
