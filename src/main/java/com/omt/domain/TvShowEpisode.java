package com.omt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tv_show_episodes")
public class TvShowEpisode extends BaseEntity {
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "tv_show_id", nullable = false)
	private TvShow tvShow;
	
	@Column(nullable = false)
	@NotNull
	private int season;
	
	@Column(nullable = false)
	@NotNull
	private int episode;
	
	@Column(columnDefinition = "TEXT")
	private String overwiev;
	
	@Column
	@DateTimeFormat
	private Date releasedDate;
	
	@Column
	@DateTimeFormat
	private Date createdDate;
	
	@Column
	@DateTimeFormat
	private Date updatedDate;

	public TvShow getTvShow() {
		return tvShow;
	}

	public void setTvShow(TvShow tvShow) {
		this.tvShow = tvShow;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public String getOverwiev() {
		return overwiev;
	}

	public void setOverwiev(String overwiev) {
		this.overwiev = overwiev;
	}

	public Date getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
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
