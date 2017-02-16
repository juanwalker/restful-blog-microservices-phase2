package com.benjsicam.restfulblog;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ImportResource("classpath:META-INF/application-security.xml")
public class SecurityConfiguration {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Autowired
	public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		authenticationProvider.setUserDetailsService(userDetailsService);
		
		return authenticationProvider;
	}	
	
	@Bean
	@Autowired
	@SuppressWarnings("deprecation")
	public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
		
		ProviderManager authenticationManager = new ProviderManager();
		ArrayList<AuthenticationProvider> authenticationProviders = new ArrayList<AuthenticationProvider>();

		authenticationProviders.add(authenticationProvider);
		authenticationManager.setProviders(authenticationProviders);

		return authenticationManager;
	}
}
