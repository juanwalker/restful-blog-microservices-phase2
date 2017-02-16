package com.benjsicam.restfulblog.service;


import com.benjsicam.restfulblog.client.AuthorClientService;
import com.benjsicam.restfulblog.client.CategoryClientService;
import com.benjsicam.restfulblog.dao.PostRepository;
import com.benjsicam.restfulblog.domain.Author;
import com.benjsicam.restfulblog.domain.Category;
import com.benjsicam.restfulblog.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private AuthorClientService authorClientService;

	@Autowired
	private CategoryClientService categoryClientService;

	@Transactional
	public void create(Post post) {
		postRepository.saveAndFlush(post);
	}

	@Transactional
	public void update(Post post) {
		postRepository.saveAndFlush(post);
	}

	@Transactional
	public void delete(Long id) {
		postRepository.delete(id);
	}

	public List<Post> find() {
		return postRepository.findAll();
	}

	public Post findById(Long id ) {
		return postRepository.findOne(id);
	}

	public Long findPostAuthor(Long id) {
		return postRepository.findPostAuthor(id);
	}

	public List<Post> findByAuthor(@PathVariable("authorId") Long authorId) {
		return postRepository.findByAuthor(authorId);
	}

	public Author findByUserId(Long postAuthorId) {
		return authorClientService.findByUserId(postAuthorId);
	}

	public List<Category> findPostCategories(Long id) {
		return categoryClientService.findCategoriesByPostId(id);
	}
}
