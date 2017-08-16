package com.omt.JsonResults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiImageResults {

    @JsonProperty("profiles")
    private List<ApiImage> profiles;

    @JsonProperty("backdrops")
    private List<ApiImage> backdrops;

    public List<String> returnApiImagePaths(List<ApiImage> images){
        List<String> apiImages = new ArrayList<>();
        for (ApiImage image: images) {
            apiImages.add("http://image.tmdb.org/t/p/w640" + image.getFilePath());
        }
        return apiImages;
    }

    public List<ApiImage> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ApiImage> profiles) {
        this.profiles = profiles;
    }
    public List<ApiImage> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<ApiImage> backdrops) {
        this.backdrops = backdrops;
    }
}
