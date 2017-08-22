package com.omt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

	public Comment() {
	}

	@Column(columnDefinition = "TEXT")
    private String commentContent;
	
    @Column
    @DateTimeFormat
    private Date createdDate;

    @Column
    @DateTimeFormat
    private Date updatedDate;

	public Comment(String commentConntent, Date createdDate, Date updatedDate) {
		super();
		this.commentContent = commentContent;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}