package com.omt.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tvshows")
public class TvShow extends Video {

    public TvShow() {
    }

    @OneToMany
    @JoinColumn(name = "tv_show_id", nullable = false)
    private List<TvShowEpisode> tvShowEpisodes;

    @ManyToMany
    @JoinTable(name="tv_show_person", joinColumns=@JoinColumn(name="tv_show_id"),
               inverseJoinColumns=@JoinColumn(name="person_id"))
    private List<Person> personList;

    @Column
    @DateTimeFormat
    @JsonProperty("first_air_date")
    private Date pilotAirDate;

    @Column
    @DateTimeFormat
    @JsonProperty("last_air_date")
    private Date finalAirDate;

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

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
}
