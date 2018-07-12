package app.service;

import app.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
	
	void login(HttpServletRequest request, UserDTO userDTO);
	boolean registerNewUserAccount(UserDTO userDTO);
	List<UserDTO> getAllUsers();
	void deleteUser(String email);
}
