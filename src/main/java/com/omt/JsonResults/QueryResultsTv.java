package com.omt.JsonResults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omt.domain.TvShow;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryResultsTv {
    private List<TvShow> tvShows;

    @JsonProperty("results")
    public List<TvShow> getTvShows() {
        return tvShows;
    }

    @JsonProperty("results")
    public void setTvShows(List<TvShow> tvShowResults) {
        this.tvShows = tvShowResults;
    }
}
