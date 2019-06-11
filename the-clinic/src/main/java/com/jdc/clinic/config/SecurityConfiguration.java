package com.jdc.clinic.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/", "/resources/**", "/login").permitAll()
				.antMatchers("/admin/**").hasRole("Admin")
				.antMatchers("/member/**").hasAnyRole("Admin", "Member")
				.antMatchers("/partner/**").hasAnyRole("Partner", "Admin")
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.usernameParameter("loginId")
				.passwordParameter("password")
				.defaultSuccessUrl("/home")
				.failureUrl("/login?error")
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder)
			.usersByUsernameQuery("select phone, password from Account where phone = ? and del_flag = false")
			.authoritiesByUsernameQuery("select phone, role from Account where phone = ?");
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
