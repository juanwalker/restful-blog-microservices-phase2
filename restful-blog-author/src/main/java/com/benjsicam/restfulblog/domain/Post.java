package com.benjsicam.restfulblog.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
    private Long id;

	@JsonIgnore
	private Long author;

    private Date date;

	@JsonIgnore
    private Set<Category> categories;

    private String content;

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
	 * @return the author
	 */
	@JsonIgnore
	public Long getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	@JsonProperty("author")
	public void setAuthor(Long author) {
		this.author = author;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the categories
	 */
	@JsonIgnore
	public Set<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	@JsonProperty("categories")
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
