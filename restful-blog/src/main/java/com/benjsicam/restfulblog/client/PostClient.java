package com.benjsicam.restfulblog.client;

import com.benjsicam.restfulblog.domain.Post;
import com.benjsicam.restfulblog.domain.PostCategory;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
public class PostClient {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    SpringCloudConfigClient springCloudConfigClient;

    public Post findPostById(Long id) {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(buildHeaders());
        return restTemplate.exchange(getPostBase().concat("/").concat(String.valueOf(id)), HttpMethod.GET,request, Post.class).getBody();
    }

    private HttpHeaders buildHeaders(){
        byte[] plainCredsBytes = springCloudConfigClient.getServiceAuthUser().getCredentials().getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        return headers;
    }

    private String getPostBase(){
        String fooResourceUrl;
        if ("docker".equals(System.getenv("SPRING_PROFILES_ACTIVE"))){
            String host = System.getenv("POST_PORT_8383_TCP_ADDR").concat(":8282");
            fooResourceUrl= "http://".concat(host).concat("/resources/author/");
        }else{
            fooResourceUrl= "http://localhost:8383/resources/posts/";
        }
        return fooResourceUrl;
    }
}
