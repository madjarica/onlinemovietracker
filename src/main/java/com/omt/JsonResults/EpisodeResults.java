package com.omt.JsonResults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omt.domain.TvShowEpisode;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EpisodeResults {

    @JsonProperty("episodes")
    List<TvShowEpisode> episodesList;

    public List<TvShowEpisode> getEpisodesList() {
        return episodesList;
    }

    public void setEpisodesList(List<TvShowEpisode> episodesList) {
        this.episodesList = episodesList;
    }
}
