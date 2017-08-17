package com.omt.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance
public class Video extends BaseEntity{

    @Column(insertable = false, updatable = false)
    private String dtype;

    @Column(nullable = false)
    @NotNull
    private String title;

    @Column
    private String originalTitle;

    @Column
    @JsonProperty("original_language")
    private String originalLanguage;

    @Column
    private String homepage;

    @Column
    @JsonProperty("imdb_id")
    private String imdbPage;

    @Column
    private String trailerLink;

    @Column(columnDefinition = "TEXT")
    private String overview;

    @Column
    @JsonProperty("poster_path")
    private String posterPath;

    @Column
    @JsonProperty("backdrop_path")
    private String backdropPath;

    @Column
    @ElementCollection(targetClass = String.class)
    private List<String> additionalBackdrops;

    @OneToMany
    private List<Character> characterList;

    @ManyToMany
    @JsonProperty(value = "genres")
    @JoinTable(name="video_genre", joinColumns=@JoinColumn(name="video_id"),
               inverseJoinColumns=@JoinColumn(name="genre_id"))
    private List<Genre> genres;

    @Column
    @DateTimeFormat
    private Date createdDate;

    @Column
    @DateTimeFormat
    private Date updatedDate;

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("name")
    public String getName() {
        return title;
    }

    @JsonProperty("name")
    public void setName(String title) {
        this.title = title;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    @JsonProperty("original_title")
    public String getOriginalTitle() {
        return originalTitle;
    }

    @JsonProperty("original_title")
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    @JsonProperty("original_name")
    public String getOriginalName() {
        return originalTitle;
    }

    @JsonProperty("original_name")
    public void setOriginalName(String originalTitle) {
        this.originalTitle = originalTitle;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public List<String> getAdditionalBackdrops() {
        return additionalBackdrops;
    }

    public void setAdditionalBackdrops(List<String> additionalBackdrops) {
        this.additionalBackdrops = additionalBackdrops;
    }

    public List<Character> getCharacterList() {
        return characterList;
    }

    public void setCharacterList(List<Character> characterList) {
        this.characterList = characterList;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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
