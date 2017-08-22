package com.omt.JsonResults;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Password {

	public Password() {}

	@JsonProperty(value = "oldPassword")
	private String oldPassword;

	@JsonProperty(value = "newPassword")
	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
