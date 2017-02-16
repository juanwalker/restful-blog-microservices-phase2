package com.benjsicam.restfulblog.service;


import com.benjsicam.restfulblog.client.CredentialsClientService;
import com.benjsicam.restfulblog.client.PostsClientService;
import com.benjsicam.restfulblog.dao.AuthorRepository;
import com.benjsicam.restfulblog.domain.Author;
import com.benjsicam.restfulblog.domain.Credentials;
import com.benjsicam.restfulblog.domain.Post;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	PostsClientService postsClientService;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private CredentialsClientService credentialsClientService;



	@Transactional
	public void create(Author author) {
		author.setPassword(passwordEncoder.encode(author.getPassword()));
		authorRepository.saveAndFlush(author);
		this.saveAuthorCredentails(author);
	}

	@Transactional
	public void update(Author author) {
		if (this.findById(author.getId())!= null){
			author.setPassword(passwordEncoder.encode(author.getPassword()));
			authorRepository.saveAndFlush(author);
			this.saveAuthorCredentails(author);
		}
	}

	@Transactional
	public void delete(Long id) {
		authorRepository.delete(id);
		credentialsClientService.deleteCredentials(id);
	}

	@HystrixCommand(fallbackMethod = "getDefaultAuthors")
	public List<Author> find() {
		return authorRepository.findAll();
	}

	public List<Author> getDefaultAuthors(){
		return new ArrayList<Author>();
	}

	@HystrixCommand(fallbackMethod = "getDefaultAuthorId")
	public Author findById(Long id) {
		return authorRepository.findOne(id);
	}

	public Author getDefaultAuthorId(Long id){
		return new Author();
	}

	@HystrixCommand(fallbackMethod = "getDefaultPosts")
	public List<Post> findAuthorPosts(Long authorId ) {
		return postsClientService.findByAuthor(authorId);
	}

	public List<Post> getDefaultPosts(Long authorId){
		return new ArrayList<Post>();
	}



	@HystrixCommand(fallbackMethod = "getDefaultAuthorUsername")
	public Author findByUsername(String userName) {
		return authorRepository.findByUsername(userName);
	}

	public Author getDefaultAuthorUsername(String userName){
		return new Author();
	}

	
	@Transactional
	public void updatePassword(Author author) {
		author.setPassword(passwordEncoder.encode(author.getPassword()));
		this.saveAuthorCredentails(author);
	}


	private void saveAuthorCredentails(Author author){
		Credentials credentials = new Credentials();
		credentials.setId(author.getId());
		credentials.setUsername(author.getUsername());
		credentials.setPassword(author.getPassword());
		credentialsClientService.saveCredentials(credentials);
	}





}
