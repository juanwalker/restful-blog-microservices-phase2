package com.benjsicam.restfulblog;

import com.benjsicam.restfulblog.domain.Credentials;
import com.benjsicam.restfulblog.service.CredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SpringBootApplication
@RestController
@RequestMapping("/resources/credentials")
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableRetry
public class RestfulBlogCredentialsApplication {

	private static final Log logger = LogFactory.getLog(RestfulBlogCredentialsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestfulBlogCredentialsApplication.class, args);
	}

	@Autowired
	private CredentialsService credentialsService;

	@RequestMapping(value = "/{username}",method = RequestMethod.GET)
	public @ResponseBody
	Credentials loadUserByUsername(@PathVariable("username") String userName) {
		logger.info("you called loadUserByUsername");
		return credentialsService.getCredentialsByUserName(userName);
	}

	@RequestMapping(value = "/",method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	Credentials save(@RequestBody Credentials credentials) {
		logger.info("you called save");
		credentialsService.saveAndFlush(credentials);
		return credentials;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		logger.info("you called delete");
		credentialsService.delete(id);
	}
}
