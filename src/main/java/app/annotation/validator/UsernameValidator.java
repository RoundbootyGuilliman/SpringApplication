package app.annotation.validator;


import app.annotation.ValidUsername;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {
	
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String USERNAME_PATTERN = "[a-zA-Z]{1}[\\w_]{2,19}";
	
	@Override
	public void initialize(ValidUsername constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context){
		
		if (StringUtils.isEmpty(username)) {
			return true;
		}
		return (validateEmail(username));
	}
	
	private boolean validateEmail(String username) {
		pattern = Pattern.compile(USERNAME_PATTERN);
		matcher = pattern.matcher(username);
		return matcher.matches();
	}
}