package com.benjsicam.restfulblog.client;


import com.benjsicam.restfulblog.domain.Author;
import com.benjsicam.restfulblog.domain.Credentials;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CredentialClient{

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	SpringCloudConfigClient springCloudConfigClient;

	public Credentials findByUsername(String userName) {

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(buildHeaders());
		return restTemplate.exchange(getCredentialsBase().concat("/").concat(String.valueOf(userName)), HttpMethod.GET,request, Credentials.class).getBody();
	}

	private HttpHeaders buildHeaders(){
		byte[] plainCredsBytes = springCloudConfigClient.getServiceAuthUser().getCredentials().getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		return headers;
	}

	private String getCredentialsBase(){
		String fooResourceUrl;
		if ("docker".equals(System.getenv("SPRING_PROFILES_ACTIVE"))){
			String host = System.getenv("CREDENTIALS_PORT_8181_TCP_ADDR").concat(":8181");
			fooResourceUrl= "http://".concat(host).concat("/resources/credentials/");
		}else{
			fooResourceUrl= "http://localhost:8181/resources/credentials/";
		}
		return fooResourceUrl;
	}
}
