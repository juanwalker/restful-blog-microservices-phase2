package com.benjsicam.restfulblog.client;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeingClientConfiguration {

    @Value("${basic-authentication.user}")
    String serviceAuthUser;

    @Value("${basic-authentication.password}")
    String serviceAuthPassword;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(serviceAuthUser, serviceAuthPassword);
    }
}
