package com.omt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryResults {
    private List<TvShow> results;
//    private List<Movie> movieResults;

    public List<TvShow> getResults() {
        return results;
    }

    public void setResults(List<TvShow> tvShowResults) {
        this.results = tvShowResults;
    }
}
