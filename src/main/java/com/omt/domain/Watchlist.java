package com.omt.domain;

import java.util.Date;
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

	public Watchlist() {
	}

	@Column(nullable = false)
	@NotNull
	@DateTimeFormat
	private Date watchDate;
	
	@Column(nullable = false)
	@NotNull
	private boolean visibleToOthers;

	@Column
	private boolean favorite;

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
    inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies;
	
	@ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "watchlist_id"),
    inverseJoinColumns = @JoinColumn(name = "tw_shows_id"))
    private Set<TvShow> tvshows;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private LoginUser LoginUser;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="watchlist_id")
	private Set<Comment> comment;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="watchlist_id")
	private Set<Rating> rating;

	public Watchlist(Date watchDate, boolean visibleToOthers, boolean favorite, String screenshot, Date createdDate, Date updatedDate, Set<Movie> movies, Set<TvShow> tvshows, com.omt.domain.LoginUser loginUser, Set<Comment> comment, Set<Rating> rating) {
		this.watchDate = watchDate;
		this.visibleToOthers = visibleToOthers;
		this.favorite = favorite;
		this.screenshot = screenshot;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.movies = movies;
		this.tvshows = tvshows;
		LoginUser = loginUser;
		this.comment = comment;
		this.rating = rating;
	}

	public Date getWatchDate() {
		return watchDate;
	}

	public void setWatchDate(Date watchDate) {
		this.watchDate = watchDate;
	}

	public boolean isVisibleToOthers() {
		return visibleToOthers;
	}

	public void setVisibleToOthers(boolean visibleToOthers) {
		this.visibleToOthers = visibleToOthers;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
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

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public Set<TvShow> getTvshows() {
		return tvshows;
	}

	public void setTvshows(Set<TvShow> tvshows) {
		this.tvshows = tvshows;
	}

	public com.omt.domain.LoginUser getLoginUser() {
		return LoginUser;
	}

	public void setLoginUser(com.omt.domain.LoginUser loginUser) {
		LoginUser = loginUser;
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

