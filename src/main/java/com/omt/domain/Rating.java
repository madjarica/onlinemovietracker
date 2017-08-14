package com.omt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ratings")
public class Rating extends BaseEntity {

	@Column
	private int rateMark;

	public Rating(int rateMark) {
		super();
		this.rateMark = rateMark;
	}

	public int getRateMark() {
		return rateMark;
	}

	public void setRateMark(int rateMark) {
		this.rateMark = rateMark;
	}
	
}
