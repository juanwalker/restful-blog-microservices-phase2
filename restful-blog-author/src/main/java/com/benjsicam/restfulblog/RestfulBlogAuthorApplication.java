package com.benjsicam.restfulblog;

import com.benjsicam.restfulblog.domain.Author;
import com.benjsicam.restfulblog.domain.PasswordChangeDTO;
import com.benjsicam.restfulblog.domain.Post;
import com.benjsicam.restfulblog.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SpringBootApplication
@RestController
@ComponentScan
@RequestMapping("/resources/author")
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class RestfulBlogAuthorApplication {

	private static final Log logger = LogFactory.getLog(RestfulBlogAuthorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestfulBlogAuthorApplication.class, args);
	}

	@Autowired
	private AuthorService authorService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@RequestBody Author entity) {
		authorService.create(entity);
		logger.info("you called create");
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody Author entity) {
		authorService.update(entity);
		logger.info( "you called update");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		authorService.delete(id);
		logger.info( "you called delete");
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<Author> find() {
		logger.info( "you called find");
		return authorService.find();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Author findAuthor(@PathVariable("id") Long id ) {
		logger.info( "you called findAuthor");
		return authorService.findById(id);
	}

	@RequestMapping(value = "{authorId}/posts", method = RequestMethod.GET)
	public @ResponseBody List<Post> findAuthorPosts(@PathVariable("authorId") Long authorId ) {
		logger.info("you called findAuthorPosts");
		return authorService.findAuthorPosts(authorId);
	}

	@RequestMapping(value = "/password", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void updatePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		Author author = authorService.findByUsername(userName);
		author.setPassword(passwordChangeDTO.getNewPassword());
		authorService.updatePassword(author);
		logger.info( "you called updatePassword");
	}
}
