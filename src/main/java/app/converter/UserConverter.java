package app.converter;

import app.dto.AbstractUser;

public class UserConverter<U1 extends AbstractUser, U2 extends AbstractUser> {
	
	public U1 convert(U1 resultUser, U2 convertedUser) {
		resultUser.setUsername(convertedUser.getUsername());
		resultUser.setEmail(convertedUser.getEmail());
		resultUser.setPassword(convertedUser.getPassword());
		return resultUser;
	}
}
