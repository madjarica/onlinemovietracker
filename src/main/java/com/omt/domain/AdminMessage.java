package com.omt.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "admin_messages")
public class AdminMessage extends BaseEntity{

    public AdminMessage() {
    }


    @Column
    @DateTimeFormat
    private Date createdDate;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String message;

    @Column
    @NotNull
    private String sentBy;

    @NotNull
    @Column
    private Boolean readState;

    @Column
    private Boolean trashed;


    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "watchlist_id")
    private Watchlist watchlist;

    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "comment_id")
    private Comment comment;


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public Boolean getReadState() {
        return readState;
    }

    public void setReadState(Boolean readState) {
        this.readState = readState;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Boolean getTrashed() {
        return trashed;
    }

    public void setTrashed(Boolean trashed) {
        this.trashed = trashed;
    }
}
