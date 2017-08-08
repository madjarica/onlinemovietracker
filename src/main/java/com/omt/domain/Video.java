package com.omt.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Inheritance
public class Video extends BaseEntity {

    @Column(insertable = false, updatable = false)
    private String dtype;

    @Column(nullable = false)
    @NotNull
    @JsonProperty("name")
    private String title;

    @Column
    @JsonProperty("original_title")
    private String originalTitle;

    @Column
    @DateTimeFormat
    @JsonProperty("first_air_date")
    private Date pilotDate;

    @Column
    @DateTimeFormat
    @JsonProperty("last_air_date")
    private Date finaleDate;

    @Column
    @JsonProperty("original_language")
    private String originalLanguage;

    @Column
    private String homepage;

    @Column
    private String facebookPage;

    @Column
    private String twitterPage;

    @Column
    private String instagramPage;

    @Column
    private String imdbPage;

    @Column
    private String trailerLink;

    @Column
    @JsonProperty("poster_path")
    private String posterLink;

    @Column(columnDefinition = "TEXT")
    private String overview;

    @Column
    @DateTimeFormat
    private Date createdDate;

    @Column
    @DateTimeFormat
    private Date updatedDate;

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Date getPilotDate() {
        return pilotDate;
    }

    public void setPilotDate(Date pilotDate) {
        this.pilotDate = pilotDate;
    }

    public Date getFinaleDate() {
        return finaleDate;
    }

    public void setFinaleDate(Date finaleDate) {
        this.finaleDate = finaleDate;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getFacebookPage() {
        return facebookPage;
    }

    public void setFacebookPage(String facebookPage) {
        this.facebookPage = facebookPage;
    }

    public String getTwitterPage() {
        return twitterPage;
    }

    public void setTwitterPage(String twitterPage) {
        this.twitterPage = twitterPage;
    }

    public String getInstagramPage() {
        return instagramPage;
    }

    public void setInstagramPage(String instagramPage) {
        this.instagramPage = instagramPage;
    }

    public String getImdbPage() {
        return imdbPage;
    }

    public void setImdbPage(String imdbPage) {
        this.imdbPage = imdbPage;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverviewString(String overview) {
        this.overview = overview;
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
