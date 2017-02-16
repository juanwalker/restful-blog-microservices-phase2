package com.benjsicam.restfulblog.service;

import com.benjsicam.restfulblog.client.PostClient;
import com.benjsicam.restfulblog.dao.CategoryRepository;
import com.benjsicam.restfulblog.domain.Category;
import com.benjsicam.restfulblog.domain.Post;
import com.benjsicam.restfulblog.domain.PostCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
	@Autowired
	PostClient postClient;

	@Autowired
	CategoryRepository categoryRepository;

	@Transactional
	public void create(Category category) {		
		categoryRepository.saveAndFlush(category);
	}
	
	@Transactional
	public void update(Category category) {		
		categoryRepository.saveAndFlush(category);
	}
	
	@Transactional
	public void delete(Long id) {
		categoryRepository.delete(id);
	}
	
	public List<Category> find() {
		return categoryRepository.findAll();
	}
	
	public Category findById(Long id) {
		return categoryRepository.findOne(id);
	}
	
	public List<Category> findCategoriesByPostId(Long id) {
		return categoryRepository.findCategoriesByPostId(id);
	}

	public Post findPost(Long id) {
		return postClient.findPostById(id);
	}
}
