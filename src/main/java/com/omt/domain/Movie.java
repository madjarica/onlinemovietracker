package com.omt.domain;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie extends Video {

    public Movie() {
    }

    @Column
    @DateTimeFormat
    @JsonProperty("release_date")
    private Date dateOfRelease;

    @Column
    private Long tmdbMovieId;

    @Column
    private boolean released;

    @Column
    @JsonProperty("runtime")
    public Long runtime;

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public boolean isReleased() {
        return released;
    }

    public void setReleased(boolean released) {
        this.released = released;
    }

    public Long getTmdbMovieId() {
        return tmdbMovieId;
    }

    public void setTmdbMovieId(Long tmdbMovieId) {
        this.tmdbMovieId = tmdbMovieId;
    }

}
