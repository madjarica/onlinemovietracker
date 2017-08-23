package com.omt.JsonResults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification {

    @JsonProperty(value = "username")
    String username;
    @JsonProperty(value = "email")
    String email;
    @JsonProperty(value = "mediaToWatch")
    String mediaToWatch;
    @JsonProperty(value = "dateScheduled")
    Date date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMediaToWatch() {
        return mediaToWatch;
    }

    public void setMediaToWatch(String mediaToWatch) {
        this.mediaToWatch = mediaToWatch;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
