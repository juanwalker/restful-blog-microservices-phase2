package com.benjsicam.restfulblog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.benjsicam.restfulblog.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query
	Category findByName(String name);
	
//	@Query(value = "SELECT C.* FROM CATEGORY C JOIN POST_CATEGORY PC ON C.ID =PC.CATEGORY_ID and PC.POST_ID = ?1 ", nativeQuery = true)
//	List<Category> findCategoriesByPostId(Long id);
	@Query("SELECT category FROM Category category JOIN category.postCategories pc WHERE pc.postId=?1")
	List<Category> findCategoriesByPostId(Long id);
}
