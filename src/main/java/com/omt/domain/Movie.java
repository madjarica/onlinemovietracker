package com.omt.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tvshows")
public class Movie extends Video {

    public Movie() {
    }
}