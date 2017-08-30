package com.omt.JsonResults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateUser {

	public UpdateUser() {}

	@JsonProperty(value = "active")
	private boolean active;

	@JsonProperty(value = "status")
	private boolean status;

	@JsonProperty(value = "subscription")
	private boolean subscription;

	@JsonProperty(value = "blockedUntil")
	private Date blockedUntil;

	@JsonProperty(value = "role")
	private boolean role;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isSubscription() {
		return subscription;
	}

	public void setSubscription(boolean subscription) {
		this.subscription = subscription;
	}

	public Date getBlockedUntil() {
		return blockedUntil;
	}

	public void setBlockedUntil(Date blockedUntil) {
		this.blockedUntil = blockedUntil;
	}

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}
}
