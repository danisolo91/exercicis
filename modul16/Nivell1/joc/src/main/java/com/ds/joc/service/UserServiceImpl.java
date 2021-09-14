package com.ds.joc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ds.joc.entity.GameType;
import com.ds.joc.entity.User;
import com.ds.joc.entity.Ranking;
import com.ds.joc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.insert(user);
	}

	/**
	 * Mètode que verifica si un nom d'usuari existeix a la BBDD
	 * 
	 * @param username
	 * @return
	 */
	public boolean usernameExists(String username) {
		return userRepository.findByUsername(username) != null;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Optional<User> getUserById(String userId) {
		return userRepository.findById(userId);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(String userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public List<Ranking> getGameAvgSuccessRate() {
		return userRepository.findGameAvgSuccessRate();
	}

	@Override
	public User getGameLoser(GameType gameType) {
		return userRepository.findGameLoser(gameType);
	}

	@Override
	public User getGameWinner(GameType gameType) {
		return userRepository.findGameWinner(gameType);
	}

}
