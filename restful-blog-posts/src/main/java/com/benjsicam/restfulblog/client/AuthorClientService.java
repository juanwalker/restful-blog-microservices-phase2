package com.benjsicam.restfulblog.client;

import com.benjsicam.restfulblog.domain.Author;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="author",configuration = FeingClientConfiguration.class)
public interface AuthorClientService {

    @RequestMapping(method = RequestMethod.GET, value="/resources/author/{authorId}")
    Author findByUserId(@PathVariable("authorId") Long authorId);
}
