package com.btewebquest;

import com.btewebquest.business.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security Configuration
 *
 * @author sfradet
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserBusinessService service;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Bean
	BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configure security settings
	 *
	 * @param http HttpSecurity
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
		.authorizeRequests()
			.antMatchers("/", "/images/**", "/CSS/**").permitAll()
				.antMatchers("/BTE/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_EDITOR")
			.and()
		.formLogin()
			.loginPage("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.permitAll()
			.defaultSuccessUrl("/", true)
			.and()
		.logout()
			.logoutUrl("/logout")
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.permitAll()
			.logoutSuccessUrl("/");	
	}

	/**
	 * Configure UserDetails
	 *
	 * @param auth AuthenticationManagerBuilder
	 * @throws Exception
	 */
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(service).passwordEncoder(passwordEncoder);
	}
}
