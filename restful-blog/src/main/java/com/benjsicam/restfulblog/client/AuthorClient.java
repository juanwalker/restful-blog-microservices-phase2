package com.benjsicam.restfulblog.client;

import com.benjsicam.restfulblog.domain.Author;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AuthorClient{

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    SpringCloudConfigClient springCloudConfigClient;

    public Author findByUserId(Long id) {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(buildHeaders());
        return restTemplate.exchange(getAuthorBase().concat("/").concat(String.valueOf(id)), HttpMethod.GET,request, Author.class).getBody();
    }

    private HttpHeaders buildHeaders(){
        byte[] plainCredsBytes = springCloudConfigClient.getServiceAuthUser().getCredentials().getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        return headers;
    }

    private String getAuthorBase(){
        String profile = applicationContext.getBean("profile",String.class);
        String fooResourceUrl;
        if ("docker".equals(System.getenv("SPRING_PROFILES_ACTIVE"))){
            String host = System.getenv("AUTHOR_PORT_8282_TCP_ADDR").concat(":8282");
            fooResourceUrl= "http://".concat(host).concat("/resources/author/");
        }else{
            fooResourceUrl= "http://localhost:8282/resources/author/";
        }
        return fooResourceUrl;
    }

}
