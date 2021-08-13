package application;

import java.util.List;

import domain.User;
import persistance.UserRepository;

public class UserController {

	private UserRepository userRepository;
	private static User loggedUser;

	public UserController() {
		userRepository = new UserRepository();
		loggedUser = null;
	}

	public List<User> getUsers() {
		return userRepository.getAllUsers();
	}

	public User getUser(int id) {
		return userRepository.getUserById(id);
	}

	public void addUser(User user) {
		userRepository.addUser(user);
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void login(String username, String password) throws Exception {
		User user = userRepository.getUserByUsernameAndPassword(username, password);

		if (user == null)
			throw new Exception("--> Nom d'usuari o contrasenya incorrecta. <--");

		loggedUser = user;
	}

	public void logout() {
		loggedUser = null;
	}
}
