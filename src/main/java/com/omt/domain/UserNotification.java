package com.omt.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user_notification")
public class UserNotification extends BaseEntity{

    @NotNull
    @Column(nullable = false)
    private String type;

    @NotNull
    @Column
    @DateTimeFormat
    private Date createdDate;

    @NotNull
    @Column
    private boolean read;

    /*
    private List<Comment> comments;
    private Watchlist watchlist;
    */
    public UserNotification(String type, Date createdDate, boolean read) {
		this.type = type;
		this.createdDate = createdDate;
		this.read = read;
	}

    public String getType() {
        return type;
    }

	public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
