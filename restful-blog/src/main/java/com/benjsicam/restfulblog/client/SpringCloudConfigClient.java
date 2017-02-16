package com.benjsicam.restfulblog.client;

import com.benjsicam.restfulblog.domain.Credentials;
import com.benjsicam.restfulblog.domain.ServiceAuthUser;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class SpringCloudConfigClient{

    @Autowired
    private ApplicationContext applicationContext;

    public ServiceAuthUser getServiceAuthUser() {

        RestTemplate rest = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(new HttpHeaders());
        return  buildServiceAuthUser(rest.getForObject(getConfigurationBase(), String.class));
    }

    private ServiceAuthUser buildServiceAuthUser(String str){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode node = objectMapper.readValue(str, JsonNode.class);
            JsonNode userNode = node.get("propertySources").get(0).get("source").get("basic-authentication.user");
            JsonNode passwordNode = node.get("propertySources").get(0).get("source").get("basic-authentication.password");
            JsonNode encodedPasswordNode = node.get("propertySources").get(0).get("source").get("basic-authentication.password-encoded");
            return new  ServiceAuthUser(userNode.getTextValue(),passwordNode.getTextValue(),encodedPasswordNode.getTextValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getConfigurationBase(){
        String fooResourceUrl;
        if ("docker".equals(System.getenv("SPRING_PROFILES_ACTIVE"))){
            String host = System.getenv("CONFIGURATION_PORT_8888_TCP_ADDR").concat(":8888");
            fooResourceUrl= "http://".concat(host).concat("/monolith/development/master/");
        }else{
            fooResourceUrl= "http://localhost:8888/monolith/development/master";
        }
        return fooResourceUrl;
    }
}
