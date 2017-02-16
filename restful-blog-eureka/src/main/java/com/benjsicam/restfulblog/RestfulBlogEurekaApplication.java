package com.benjsicam.restfulblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RestfulBlogEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulBlogEurekaApplication.class, args);
	}
}
