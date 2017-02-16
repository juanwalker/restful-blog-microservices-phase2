package com.benjsicam.restfulblog.domain;

import java.util.Set;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "category")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "name", length = 50, nullable = false, unique = true)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
	private Set<PostCategory> postCategories;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Set<PostCategory> getPostCategories() {
		return postCategories;
	}

	public void setPostCategories(Set<PostCategory> postCategories) {
		this.postCategories = postCategories;
	}
}
