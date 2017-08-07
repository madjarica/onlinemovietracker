package com.omt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tvshows")
public class TvShow extends BaseEntity {

	@Column(unique = true, nullable = false)
	@NotNull
	private String title;
	
	@Column
	private String originalTitle;
	
	@Column
	@DateTimeFormat
	private Date pilotDate;

}
