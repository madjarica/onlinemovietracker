package com.omt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tvshows")
public class TvShow extends BaseEntity {

	@Column(unique = true, nullable = false)
	@NotNull
	private String title;
	
	@Column
	private String originalTitle;
	
	@Column
	@DateTimeFormat
	private Date pilotDate;
	
	@Column
	@DateTimeFormat
	private Date finaleDate;
	
	@Column
	private String originalLanguage;
	
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
	
	@Column
	private String posterLink;
	
	@Column(columnDefinition = "TEXT")
	private String overwiev;

	@Column
	@DateTimeFormat
	private Date createdDate;
	
	@Column
	@DateTimeFormat
	private Date updatedDate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public Date getPilotDate() {
		return pilotDate;
	}

	public void setPilotDate(Date pilotDate) {
		this.pilotDate = pilotDate;
	}

	public Date getFinaleDate() {
		return finaleDate;
	}

	public void setFinaleDate(Date finaleDate) {
		this.finaleDate = finaleDate;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getFacebookPage() {
		return facebookPage;
	}

	public void setFacebookPage(String facebookPage) {
		this.facebookPage = facebookPage;
	}

	public String getTwitterPage() {
		return twitterPage;
	}

	public void setTwitterPage(String twitterPage) {
		this.twitterPage = twitterPage;
	}

	public String getInstagramPage() {
		return instagramPage;
	}

	public void setInstagramPage(String instagramPage) {
		this.instagramPage = instagramPage;
	}

	public String getImdbPage() {
		return imdbPage;
	}

	public void setImdbPage(String imdbPage) {
		this.imdbPage = imdbPage;
	}

	public String getTrailerLink() {
		return trailerLink;
	}

	public void setTrailerLink(String trailerLink) {
		this.trailerLink = trailerLink;
	}

	public String getPosterLink() {
		return posterLink;
	}

	public void setPosterLink(String posterLink) {
		this.posterLink = posterLink;
	}

	public String getOverwiev() {
		return overwiev;
	}

	public void setOverwiev(String overwiev) {
		this.overwiev = overwiev;
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
