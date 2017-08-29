package com.omt.domain;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "watchlist_collection")
public class WatchlistCollection extends BaseEntity{

    @OneToMany
    @JoinColumn(name = "watchlist_collection_id")
    private Set<Watchlist> watchlistElements;

    @OneToMany
    @JoinColumn(name = "watchlist_collection_id")
    private Set<Comment> comment;

    @Column
    private String username;

    public Set<Watchlist> getWatchlistElements() {
        return watchlistElements;
    }

    public void setWatchlistElements(Set<Watchlist> watchlistElements) {
        this.watchlistElements = watchlistElements;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
