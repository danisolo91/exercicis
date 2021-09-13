package com.ds.joc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ds.joc.entity.GameType;
import com.ds.joc.entity.User;
import com.ds.joc.entity.Ranking;
import com.ds.joc.entity.Role;
import com.ds.joc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${appSecurity.anonUserPass}")
	private String anonUserPass;

	/**
	 * Mètode que s'encarrega de crear nous usuaris. Si no es proporciona un
	 * username, es considera usuari anònim. Els usuaris anònims reben el nom
	 * "Anònim" i com a username el propi ID per poder trobar-lo a la BBDD.
	 */
	public User createUser(String username, String password, List<Role> roles) throws Exception {
		User user = null;

		// Usuaris registrats
		if (username != null && !username.isBlank()) {
			if (password == null || password.isBlank()) {
				throw new Exception("Has d'introduïr una contrasenya.");
			}
			if (usernameExists(username)) {
				throw new Exception("El nom d'usuari introduït ja existeix.");
			}
			user = addUser(new User(username, password, roles));
		}
		// Usuaris anonims
		else {
			user = addUser(new User(username, anonUserPass, roles));
			user.setUsername("Anònim");
			user.setAnonymous(true);
			updateUser(user);
		}

		return user;
	}

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
