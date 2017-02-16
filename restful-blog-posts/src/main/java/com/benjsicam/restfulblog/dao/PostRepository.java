package com.benjsicam.restfulblog.dao;

import com.benjsicam.restfulblog.domain.Category;
import com.benjsicam.restfulblog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {	
	@Query
	List<Post> findByAuthor(Long authorId);
	
	@Query
	List<Post> findByDate(Date date);

	@Query("SELECT post.author FROM Post post WHERE post.id=?1")
	Long findPostAuthor(Long id);

}
