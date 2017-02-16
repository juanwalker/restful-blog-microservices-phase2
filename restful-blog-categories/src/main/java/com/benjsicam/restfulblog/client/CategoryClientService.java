package com.benjsicam.restfulblog.client;

import com.benjsicam.restfulblog.domain.Category;
import com.benjsicam.restfulblog.domain.Post;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name="monolith",configuration = FeingClientConfiguration.class)
public interface CategoryClientService {

    @RequestMapping(method = RequestMethod.GET, value="/resources/category/post/{postId}")
    List<Category> findCategoriesByPostId(@PathVariable("postId") Long postId);

    @RequestMapping(method = RequestMethod.GET, value="/resources/category/{id}/posts")
    List<Post> findPostsByCategoryId(Long id);
}
