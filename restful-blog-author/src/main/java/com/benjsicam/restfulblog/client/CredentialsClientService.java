package com.benjsicam.restfulblog.client;

import com.benjsicam.restfulblog.domain.Credentials;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="credentials",configuration = FeingClientConfiguration.class)
public interface CredentialsClientService {

    @RequestMapping(method = RequestMethod.POST, value="/resources/credentials/",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    void  saveCredentials(@RequestBody Credentials credentials);

    @RequestMapping(method = RequestMethod.GET, value="/resources/credentials/{userName}")
    Credentials  loadCredentialsByUserName(@PathVariable("userName") String  userName);

    @RequestMapping(method = RequestMethod.DELETE, value="/resources/credentials/{id}")
    void  deleteCredentials(@PathVariable("id") Long  userId);
}
