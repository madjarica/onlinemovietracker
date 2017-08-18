package com.omt.JsonResults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omt.domain.Keyword;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KeywordsResults {

    @JsonProperty("results")
    private List<Keyword> keywords;

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }
}
