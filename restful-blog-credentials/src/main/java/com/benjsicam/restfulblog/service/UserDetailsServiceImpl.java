package com.benjsicam.restfulblog.service;

import com.benjsicam.restfulblog.domain.Credentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("deprecation")
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Value("${basic-authentication.user}")
	private String serviceAuthUser;

	@Value("${basic-authentication.password-encoded}")
	private String serviceAuthPassword;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if(!username.equals(serviceAuthUser)) {
			throw new UsernameNotFoundException("Username or password is invalid.");
		}else{
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new  SimpleGrantedAuthority("ROLE_SERVICE"));
			return new User(serviceAuthUser, serviceAuthPassword, true, true, true, true, authorities);
		}


	}

}