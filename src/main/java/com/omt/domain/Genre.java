package com.omt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "genres")
public class Genre extends BaseEntity{

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    public Genre(String name) {

		this.name = name;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
