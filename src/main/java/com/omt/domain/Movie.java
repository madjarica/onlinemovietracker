package com.omt.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "movies")
public class Movie extends Video {

    public Movie() {
    }

    @Column
    @DateTimeFormat
    @JsonProperty("release_date")
    private Date dateOfRelease;

    @Column
    @JsonProperty("id")
    private Long TMDBMovieId;

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

    public Long getTMDBMovieId() {
        return TMDBMovieId;
    }

    public void setTMDBMovieId(Long TMDBMovieId) {
        this.TMDBMovieId = TMDBMovieId;
    }

}
