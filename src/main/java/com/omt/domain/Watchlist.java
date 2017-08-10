package com.omt.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "watchlists")
public class Watchlist extends BaseEntity {


    @Column(nullable = false)
    @NotNull
    @DateTimeFormat
    private Date watchDate;

    @Column(nullable = false)
    @NotNull
    private boolean visibleToOthers;

    @Column
    private boolean favorite;

    @Column
    private String screenshot;

    @Column
    @DateTimeFormat
    private Date createdDate;

    @Column
    @DateTimeFormat
    private Date updatedDate;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "watchlist_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "watchlist_id"),
            inverseJoinColumns = @JoinColumn(name = "tw_shows_id"))
    private Set<TvShow> tvshows;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private OmtUser user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "watchlist_id")
    private Set<Comment> comment;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bookmark_id")
    private Set<Rating> rating;


}
