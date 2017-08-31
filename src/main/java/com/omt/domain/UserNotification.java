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

    @Column
    @DateTimeFormat
    private Date createdDate;

    @Column
    private Boolean readState;
    
    @Column
    private String sender;

    @Column
    private String reciver;

    @Column
    private Boolean trashed;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "watchlist_id")
    private Watchlist watchlist;

    @Column
    private String message;

    @Column
    private Long comment;

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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public Boolean getTrashed() {
        return trashed;
    }

    public void setTrashed(Boolean trashed) {
        this.trashed = trashed;
    }

    public Long getComment() {
        return comment;
    }

    public void setComment(Long comment) {
        this.comment = comment;
    }
}
