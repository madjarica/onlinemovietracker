package com.omt.JsonResults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.omt.domain.Character;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditsResults {
    @JsonProperty("cast")
    private List<Character> characters;

    @JsonProperty("id")
    private Long tmdbMediaId;

	@JsonProperty("job")
	private String job;

    public List<Character> getObject() {
        return characters;
    }

    public void setObject(List<Character> characters) {
        this.characters = characters;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public Long getTmdbMediaId() {
        return tmdbMediaId;
    }

    public void setTmdbMediaId(Long tmdbMediaId) {
        this.tmdbMediaId = tmdbMediaId;
    }

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
}
