package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8", true);
		
		http
			.authorizeRequests()
				.antMatchers("/", "/main", "/news", "/openReg", "/register").permitAll()
				.antMatchers("/comment", "/add", "/saveNews", "/userPage").hasRole("USER")
				.antMatchers("/delete", "/edit", "/deleteComments", "/adminPage", "/deleteUser").hasRole("ADMIN")
				.anyRequest().permitAll()
			.and()
			
			.formLogin()
				.permitAll()
				.loginPage("/loginPage")
				.defaultSuccessUrl("/main")
				.failureUrl("/loginPage?error")
				.usernameParameter("email")
			.and()
			
			.logout()
				.permitAll()
				.logoutSuccessUrl("/main")
			.and()
			
			.addFilterBefore(filter, CsrfFilter.class);
	}
}