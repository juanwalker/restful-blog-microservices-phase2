package com.benjsicam.restfulblog.service;

import com.benjsicam.restfulblog.client.CredentialClient;
import com.benjsicam.restfulblog.client.SpringCloudConfigClient;
import com.benjsicam.restfulblog.domain.Credentials;
import com.benjsicam.restfulblog.domain.ServiceAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
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
	
	@Autowired
	private CredentialClient credentialService;

	@Autowired SpringCloudConfigClient springCloudConfigClient;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ServiceAuthUser serviceAuthUser = springCloudConfigClient.getServiceAuthUser();
		if (username.equals(serviceAuthUser.getUser())){

			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_SERVICE"));
			return new User(serviceAuthUser.getUser(), serviceAuthUser.getEncodedPassword(), true, true, true, true, authorities);

		}else{
			Credentials credentials = credentialService.findByUsername(username);
			if(credentials == null) {
				throw new UsernameNotFoundException("Username or password is invalid.");
			}
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for(String role: credentials.getRoles()){
				authorities.add(new GrantedAuthorityImpl(role));
			}
			return new User(credentials.getUsername(), credentials.getPassword(), true, true, true, true, authorities);
		}
	}



	private UserDetails createUserDetails(Credentials credentials){
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		return new User(credentials.getUsername(), credentials.getPassword(), true, true, true, true, authorities);
	}

}