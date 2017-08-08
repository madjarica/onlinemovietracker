package com.omt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "watchlists")
public class Movies extends BaseEntity {

	
	@Column(nullable = false)
	@NotNull
	private String title;
	 
	@Column
	private String originalTitle;
	   
	@Column
	private boolean realised;
	
	@Column
	@DateTimeFormat
	private Date dateOfRealease;
	   
	@Column
	private String originalLanguage;
	 
	@Column
	private int runtime;
	   
	@Column
	private String homepage;
	   
	@Column
	private String facebookPage;
	   
	@Column
	private String twitterPage;
	   
	@Column
	private String instagramPage;
	   
	@Column
	private String imdbPage;
	
	@Column
	private String trailerLink;
	   
	@Column(columnDefinition = "TEXT")
	private String overview;
	   
	@Column
	private String poster;
	
	
	
}
