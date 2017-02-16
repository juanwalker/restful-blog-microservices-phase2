package com.benjsicam.restfulblog;

import com.benjsicam.restfulblog.domain.Author;
import com.benjsicam.restfulblog.domain.Category;
import com.benjsicam.restfulblog.domain.Post;
import com.benjsicam.restfulblog.service.PostService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@ComponentScan
@RequestMapping("/resources/posts")
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class RestfulBlogPostsApplication {

	private static final Log logger = LogFactory.getLog(RestfulBlogPostsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestfulBlogPostsApplication.class, args);
	}

	@Autowired
	private PostService postService;


	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@RequestBody Post post) {

		postService.create(post);
		logger.info("create called");
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody Post post) {

		postService.update(post);
		logger.info("update called");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus( HttpStatus.OK )
	public void delete(@PathVariable("id") Long id) {

		postService.delete(id);
		logger.info("delete called");
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Post> find() {

		return postService.find();
	}
	@RequestMapping(value = "/author/{authorId}",method = RequestMethod.GET)
	public @ResponseBody List<Post> findByAuthor(@PathVariable("authorId") Long authorId) {
		logger.info("findByAuthor called");
		return postService.findByAuthor(authorId);
	}



	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Post findPost(@PathVariable("id") Long id ) {
		logger.info("findById called");
		return postService.findById(id);
	}

	@RequestMapping(value = "/{id}/author", method = RequestMethod.GET)
	public @ResponseBody
	Author getPostAuthor(@PathVariable("id") Long id) {
		Long postAuthorId = postService.findPostAuthor(id);
		logger.info("findByUserId called");
		return postService.findByUserId(postAuthorId);
	}

	@RequestMapping(value = "/{id}/category", method = RequestMethod.GET)
	public @ResponseBody List<Category> findPostCategories(@PathVariable("id") Long id ) {
		logger.info("findPostCategories called");
		return postService.findPostCategories(id);
	}

}
