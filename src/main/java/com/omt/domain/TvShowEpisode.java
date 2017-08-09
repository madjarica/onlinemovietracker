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

    public TvShowEpisode() {
    }

    @Column(nullable = false)
    @NotNull
    private int season;

    @Column(nullable = false)
    @NotNull
    private int episode;

    @Column(columnDefinition = "TEXT")
    private String overview;

    @Column
    @DateTimeFormat
    private Date releasedDate;

    @Column
    @DateTimeFormat
    private Date createdDate;

    @Column
    @DateTimeFormat
    private Date updatedDate;

    public TvShowEpisode(int season, int episode, String overview, Date releasedDate, Date createdDate,
                         Date updatedDate) {

        this.season = season;
        this.episode = episode;
        this.overview = overview;
        this.releasedDate = releasedDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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
