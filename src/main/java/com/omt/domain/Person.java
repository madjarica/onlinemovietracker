package com.omt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "persons")
public class Person extends BaseEntity {

    public Person() {
    }

    @Column
    private String role;

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String biography;

    @Column
    private String picture;

    @Column
    @DateTimeFormat
    private Date birthday;

    @Column
    private String placeOfBirth;

    @Column(unique = true)
    private Long tmdbPersonId;

    @Column
    @DateTimeFormat
    private Date createdDate;

    @Column
    @DateTimeFormat
    private Date updatedDate;

    public Person(String role, String name, String biography, String picture, Date birthday, String placeOfBirth,
                  Long tmdbPersonId, Date createdDate, Date updatedDate) {

        this.role = role;
        this.name = name;
        this.biography = biography;
        this.picture = picture;
        this.birthday = birthday;
        this.placeOfBirth = placeOfBirth;
        this.tmdbPersonId = tmdbPersonId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
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

    public Long getTmdbPersonId() {
        return tmdbPersonId;
    }

    public void setTmdbPersonId(Long tmdbPersonId) {
        this.tmdbPersonId = tmdbPersonId;
    }
}
