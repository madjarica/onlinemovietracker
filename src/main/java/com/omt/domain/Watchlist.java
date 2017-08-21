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

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "watchlists")
public class Watchlist extends BaseEntity{

	public Watchlist() {
	}

	@Column(nullable = false)
	@NotNull
	@DateTimeFormat
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
	
	@ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "watchlist_id"),
    inverseJoinColumns = @JoinColumn(name = "video_id"))
    private List<Video> videos;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private LoginUser loginUser;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
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

	public Boolean isVisibleToOthers() {
		return visibleToOthers;
	}

	public void setVisibleToOthers(Boolean visibleToOthers) {
		this.visibleToOthers = visibleToOthers;
	}

	public Boolean isFavourite() {
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

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}


	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
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
}

