package com.benjsicam.restfulblog.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "post_category")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostCategory {

	@Id
	@Column
	private Long id;

	@Column(name="post_id")
    private Long postId;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
    private Category category;

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		postId = postId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		category = category;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
