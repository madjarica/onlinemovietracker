package com.omt.JsonResults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omt.domain.Movie;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryResultsMovie {
    private List<Movie> movies;
    @JsonProperty("videos")
    private List<Object> trailers;

    @JsonProperty("results")
    public List<Movie> getMovies() {
        return movies;
    }

    @JsonProperty("results")
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Object> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<Object> trailers) {
        this.trailers = trailers;
    }
}
