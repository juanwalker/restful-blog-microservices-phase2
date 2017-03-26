package com.benjsicam.restfulblog.service;

import com.benjsicam.restfulblog.dao.CredentialsRepository;
import com.benjsicam.restfulblog.domain.Credentials;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {

    @Autowired
    CredentialsRepository credentialsRepository;

    @HystrixCommand(fallbackMethod = "getDefaultCredentials")
    public Credentials getCredentialsByUserName(String userName){
        Credentials credentials = this.credentialsRepository.findByUsername(userName);
        credentials.getRoles().add("ROLE_USER");
        return credentials;
    }

    public Credentials getDefaultCredentials(String userName){
        return new Credentials();
    }

    public void saveAndFlush(Credentials credentials){
        this.credentialsRepository.saveAndFlush(credentials);
    }

    public void delete(Long id){
        this.credentialsRepository.delete(id);
    }

}
