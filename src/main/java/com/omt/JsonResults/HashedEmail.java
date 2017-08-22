package com.omt.JsonResults;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HashedEmail {

	public HashedEmail() {}

	@JsonProperty(value = "hashedEmail")
	private String hashedEmail;

	public String getHashedEmail() {
		return hashedEmail;
	}

	public void setHashedEmail(String hashedEmail) {
		this.hashedEmail = hashedEmail;
	}
}
