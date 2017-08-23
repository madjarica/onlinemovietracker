package com.omt.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user_notification")
public class UserNotification extends BaseEntity{

    public UserNotification() {
    }

    @Column(nullable = true)
    private String type;

    @NotNull
    @Column
    @DateTimeFormat
    private Date createdDate;

    @Column
    private Boolean readState;

    @ManyToOne
    @JoinColumn(name = "watchlist_id")
    private Watchlist watchlist;

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

    public Boolean isRead() {
        return readState;
    }

    public void setRead(Boolean read) {
        this.readState = read;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }
}
