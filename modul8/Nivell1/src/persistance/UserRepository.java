package persistance;

import java.util.ArrayList;
import java.util.List;

import domain.User;

public class UserRepository {

	private List<User> users = new ArrayList<User>();

	public List<User> getAllUsers() {
		return users;
	}

	public User getUserById(int id) {
		return users.get(id - 1);
	}

	public void addUser(User user) {
		users.add(user);
	}
	
	public User getUserByUsername(String username) {
		User user = null;

		for (User u : users) {
			if (u.getUsername().equals(username)) {
				user = u;
			}
		}

		return user;
	}

	public User getUserByUsernameAndPassword(String username, String password) {
		User user = null;

		for (User u : users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				user = u;
			}
		}

		return user;
	}
}
