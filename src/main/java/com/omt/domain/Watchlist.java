package com.omt.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "watchlists")
public class Watchlist extends BaseEntity{

	@Column(nullable = false)
	private Date watchDate;
	
	@Column(nullable = false)
	@NotNull
	private Boolean visibleToOthers;

	@Column
	private Boolean favourite;

	@Column
	private String screenshot;

	@Column
	@DateTimeFormat
	private Date createdDate;

	@Column
	@DateTimeFormat
	private Date updatedDate;
	
	@Column
	private float averageRate;
	
	@Column
	private int numberOfComments;
	
	@ManyToOne
	@JoinColumn(name = "video_id", nullable = false)
	private Video video;
	
	@NotNull
	@Column(nullable = false)
	private String watchlistUser;
	
	@OneToMany(cascade = CascadeType.REFRESH, orphanRemoval = true)
	@JoinColumn(name="watchlist_id")
	private Set<Comment> comment;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="watchlist_id")
	private Set<Rating> rating;

	public Date getWatchDate() {
		return watchDate;
	}

	public void setWatchDate(Date watchDate) {
		this.watchDate = watchDate;
	}

	public Boolean getVisibleToOthers() {
		return visibleToOthers;
	}

	public void setVisibleToOthers(Boolean visibleToOthers) {
		this.visibleToOthers = visibleToOthers;
	}

	public Boolean getFavourite() {
		return favourite;
	}

	public void setFavourite(Boolean favourite) {
		this.favourite = favourite;
	}

	public String getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
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

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public String getWatchlistUser() {
		return watchlistUser;
	}

	public void setWatchlistUser(String watchlistUser) {
		this.watchlistUser = watchlistUser;
	}

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

	public Set<Rating> getRating() {
		return rating;
	}

	public void setRating(Set<Rating> rating) {
		this.rating = rating;
	}
	
	public float getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(float averageRate) {
		this.averageRate = averageRate;
	}

	public int getNumberOfComments() {
		return numberOfComments;
	}

	public void setNumberOfComments(int numberOfComments) {
		this.numberOfComments = numberOfComments;
	}

	public Watchlist() {
	}
}

