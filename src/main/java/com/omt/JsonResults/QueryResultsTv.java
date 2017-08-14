package com.omt.JsonResults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omt.domain.TvShow;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryResultsTv {
    private List<TvShow> tvShows;
    private String imdb_id;

    @JsonProperty("results")
    public List<TvShow> getTvShows() {
        return tvShows;
    }

    @JsonProperty("results")
    public void setTvShows(List<TvShow> tvShowResults) {
        this.tvShows = tvShowResults;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }
}
