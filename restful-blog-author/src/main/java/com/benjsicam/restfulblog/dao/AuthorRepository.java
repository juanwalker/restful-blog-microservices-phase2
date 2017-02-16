package com.benjsicam.restfulblog.dao;

import com.benjsicam.restfulblog.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	Author findByUsername(String username);
}
