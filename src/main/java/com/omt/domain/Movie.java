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

    @ManyToMany
    @JoinTable(name="movie_person", joinColumns=@JoinColumn(name="movie_id"),
            inverseJoinColumns=@JoinColumn(name="person_id"))
    private List<Person> personList;

    @Column
    private boolean released;

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

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
