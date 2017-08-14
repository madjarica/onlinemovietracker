package com.omt.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tvshows")
public class TvShow extends Video {

    public TvShow() {
    }

    @OneToMany
    @JoinColumn(name = "tv_show_id", nullable = false)
    private List<TvShowEpisode> tvShowEpisodes;

    @Column
    @DateTimeFormat
    @JsonProperty("first_air_date")
    private Date pilotAirDate;

    @Column
    @DateTimeFormat
    @JsonProperty("last_air_date")
    private Date finalAirDate;

    @Column(unique = true)
    private Long tmdbTvShowId;

    @Column
    @JsonProperty("number_of_seasons")
    private Integer numberOfSeasons;


    public List<TvShowEpisode> getTvShowEpisodes() {
        return tvShowEpisodes;
    }

    public void setTvShowEpisodes(List<TvShowEpisode> tvShowEpisodes) {
        this.tvShowEpisodes = tvShowEpisodes;
    }

    public Date getPilotAirDate() {
        return pilotAirDate;
    }

    public void setPilotAirDate(Date pilotAirDate) {
        this.pilotAirDate = pilotAirDate;
    }

    public Date getFinalAirDate() {
        return finalAirDate;
    }

    public void setFinalAirDate(Date finalAirDate) {
        this.finalAirDate = finalAirDate;
    }

    public Long getTmdbTvShowId() {
        return tmdbTvShowId;
    }

    public void setTmdbTvShowId(Long tmdbTvShowId) {
        this.tmdbTvShowId = tmdbTvShowId;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }
}
