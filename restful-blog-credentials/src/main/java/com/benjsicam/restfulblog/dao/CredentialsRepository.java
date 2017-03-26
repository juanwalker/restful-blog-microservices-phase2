package com.benjsicam.restfulblog.dao;

import com.benjsicam.restfulblog.domain.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
	@Query
	Credentials findByUsername(String username);
}
