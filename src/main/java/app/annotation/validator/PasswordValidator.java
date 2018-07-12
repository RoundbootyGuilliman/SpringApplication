package app.annotation.validator;

import app.annotation.ValidPassword;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
	
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String PASSWORD_PATTERN = "[\\w]{3,20}";
	
	@Override
	public void initialize(ValidPassword constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context){
		if (StringUtils.isEmpty(password)) {
			return true;
		}
		return (validateEmail(password));
	}
	
	private boolean validateEmail(String password) {
		pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(password);
		return matcher.matches();
	}
}