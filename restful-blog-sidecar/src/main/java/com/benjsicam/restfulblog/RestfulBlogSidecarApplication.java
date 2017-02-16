package com.benjsicam.restfulblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@SpringBootApplication
@EnableSidecar
public class RestfulBlogSidecarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulBlogSidecarApplication.class, args);
	}
}
