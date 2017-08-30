package com.omt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tv_show_episodes")
public class TvShowEpisode extends BaseEntity {

    public TvShowEpisode() {
    }

    @Column(nullable = false)
    @NotNull
    @JsonProperty("season_number")
    private int season;

    @Column(nullable = false)
    @NotNull
    @JsonProperty("episode_number")
    private int episode;

    @Column
    @JsonProperty("name")
    private String name;

    @Column(columnDefinition = "TEXT")
    private String overview;

    @Column(insertable= false, updatable = false)
    private Long tv_show_id;

    @Column
    @DateTimeFormat
    @JsonProperty("air_date")
    private Date releasedDate;

    @Column
    private String still;

    @Column
    @DateTimeFormat
    private Date createdDate;

    @Column
    @DateTimeFormat
    private Date updatedDate;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTv_show_id() {
        return tv_show_id;
    }

    public void setTv_show_id(Long tv_show_id) {
        this.tv_show_id = tv_show_id;
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

    public String getStill() {
        return still;
    }

    public void setStill(String still) {
        this.still = still;
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
