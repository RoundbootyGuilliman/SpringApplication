package app.dao;

import app.entity.User;

import java.util.List;

public interface UserDAO {
	
	void saveUser(User user);
	List<User> getAllUsers();
	User getByEmail(String email);
	void deleteUser(String email);
}
