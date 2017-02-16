package com.benjsicam.restfulblog.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "post")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private Long author;

	@Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

	@JsonIgnore
	@Transient
	private Set<Category> categories;

	@Column(name = "content", columnDefinition="TEXT")
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
