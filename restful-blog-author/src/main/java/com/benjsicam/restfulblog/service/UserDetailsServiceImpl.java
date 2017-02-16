package com.benjsicam.restfulblog.service;

import com.benjsicam.restfulblog.client.CredentialsClientService;
import com.benjsicam.restfulblog.domain.Credentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Value("${basic-authentication.user}")
	String serviceAuthUser;

	@Value("${basic-authentication.password-encoded}")
	String serviceAuthPassword;

	@Autowired
	private CredentialsClientService credentialsClientService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username.equals(serviceAuthUser)){
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_SERVICE"));
			return new User(serviceAuthUser,serviceAuthPassword, true, true, true, true, authorities);
		}else{
			Credentials credentials = credentialsClientService.loadCredentialsByUserName(username);
			if(credentials == null) {
				throw new UsernameNotFoundException("Username or password is invalid.");
			}
			return this.createUserDetails(credentials);
		}
	}

	private UserDetails createUserDetails(Credentials credentials){
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		credentials.getRoles().stream().forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
		return new User(credentials.getUsername(), credentials.getPassword(), true, true, true, true, authorities);
	}
}