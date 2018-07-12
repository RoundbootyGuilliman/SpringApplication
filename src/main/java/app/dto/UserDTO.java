package app.dto;

import app.annotation.PasswordMatches;
import app.annotation.ValidEmail;
import app.annotation.ValidPassword;
import app.annotation.ValidUsername;

import javax.validation.constraints.NotEmpty;

@PasswordMatches(message = "{valid.match}")
public class UserDTO implements AbstractUser {
	
	@NotEmpty(message = "{valid.notEmpty}")
	@ValidUsername(message = "{valid.username}")
	private String username;
	
	@NotEmpty(message = "{valid.notEmpty}")
	@ValidPassword(message = "{valid.password}")
	private String password;
	private String matchingPassword;
	
	@NotEmpty(message = "{valid.notEmpty}")
	@ValidEmail(message = "{valid.email}")
	private String email;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMatchingPassword() {
		return matchingPassword;
	}
	
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}