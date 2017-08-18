package com.omt.JsonResults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omt.domain.Keyword;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KeywordsResults {

    private List<Keyword> keywords;

    @JsonProperty("results")
    public List<Keyword> getKeywords() {
        return keywords;
    }

    @JsonProperty("results")
    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    @JsonProperty("keywords")
    public List<Keyword> getMovieKeywords(){
        return keywords;
    }

    @JsonProperty("keywords")
    public void setMovieKeywords(List<Keyword> keywords){
        this.keywords = keywords;
    }

}
