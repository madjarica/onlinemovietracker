package com.omt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "characters")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Character extends BaseEntity{

    public Character() {
    }

    @Column
    @JsonProperty("character")
    private String name;

    @Column
    @JsonProperty("actor_id")
    private Long actorId;

    @Column
    private Long tmdbMediaId;

    @ManyToOne
    private Person person;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public Long getTmdbMediaId() {
        return tmdbMediaId;
    }

    public void setTmdbMediaId(Long tmdbMediaId) {
        this.tmdbMediaId = tmdbMediaId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
