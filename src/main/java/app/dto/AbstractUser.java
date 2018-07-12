package app.dto;

public interface AbstractUser {
	
	String getUsername();
	void setUsername(String username);
	String getEmail();
	void setEmail(String email);
	String getPassword();
	void setPassword(String password);
}
