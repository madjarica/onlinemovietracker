package com.omt.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ratings")
public class Rating extends BaseEntity {

	@Column
	private int rateMark;

//	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name="watchlist_id")
//	private Set<Rating> rating;

	public Rating() {
	}

	public int getRateMark() {
		return rateMark;
	}

	public void setRateMark(int rateMark) {
		this.rateMark = rateMark;
	}

//	public Set<Rating> getRating() {
//		return rating;
//	}
//
//	public void setRating(Set<Rating> rating) {
//		this.rating = rating;
//	}
}
