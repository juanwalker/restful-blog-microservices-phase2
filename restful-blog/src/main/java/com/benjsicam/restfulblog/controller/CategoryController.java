package com.benjsicam.restfulblog.controller;

import com.benjsicam.restfulblog.domain.Category;
import com.benjsicam.restfulblog.domain.Post;
import com.benjsicam.restfulblog.domain.PostCategory;
import com.benjsicam.restfulblog.service.CategoryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/resources/category")
public class CategoryController {

	private static final Log logger = LogFactory.getLog(CategoryController.class);


	@Autowired
	private CategoryService categoryService;
	

	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void create(@RequestBody Category entity) {
		logger.info("create called");
		categoryService.create(entity);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody Category entity) {
		logger.info("update called");
		categoryService.update(entity);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		logger.info("delete called");
		categoryService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Category> find() {
		logger.info("find called");
		return categoryService.find();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Category findCategory(@PathVariable("id") Long id ) {
		logger.info("findCategory called");
		return categoryService.findById(id);
	}
	
	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public @ResponseBody List<Post> findCategoryPosts(@PathVariable("id") Long id ) {

		List<Post> posts = new ArrayList<Post>();
		Category category = categoryService.findById(id);
		for (PostCategory pc: category.getPostCategories()){
			posts.add(categoryService.findPost(pc.getPostId()));
		}
		logger.info("findCategoryPosts called");
		return posts;
	}

	@RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Category> findCategoriesByPostId(@PathVariable("id") Long id ) {
		logger.info("findCategoriesByPostId called");
		return categoryService.findCategoriesByPostId(id);
	}
}
