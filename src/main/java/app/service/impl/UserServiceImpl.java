package app.service.impl;

import app.converter.UserConverter;
import app.dao.UserDAO;
import app.dto.UserDTO;
import app.entity.Role;
import app.entity.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	UserConverter<User, UserDTO> dtoToEntity = new UserConverter<>();
	UserConverter<UserDTO, User> entityToDto = new UserConverter<>();
	
	@Override
	public void login(HttpServletRequest request, UserDTO userDTO) {
		try {
			request.login(userDTO.getEmail(), userDTO.getPassword());
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean registerNewUserAccount(UserDTO userDTO) {

		if (emailExists(userDTO.getEmail())) {
			return false;
		}
		User user = dtoToEntity.convert(new User(), userDTO);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.saveUser(user);
		user.setRoles(Arrays.asList(new Role(user.getId(), "USER")));
		userDAO.saveUser(user);
		return true;
	}
	
	private boolean emailExists(String email) {
		User user = userDAO.getByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}
	
	public List<UserDTO> getAllUsers() {
		
		List<UserDTO> listOfUsers = new ArrayList<>();
		for (User user : userDAO.getAllUsers()) {
			listOfUsers.add(entityToDto.convert(new UserDTO(), user));
		}
		return listOfUsers;
	}
	
	@Override
	public void deleteUser(String email) {
		userDAO.deleteUser(email);
	}
}