package com.omt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "persons")
public class Person extends BaseEntity {

	@Column
	private String role;

	@Column
	private String name;

	@Column(columnDefinition = "TEXT")
	private String biography;

	@Column
	private String picture;

	@Column
	@DateTimeFormat
	private Date birthday;

	@Column
	private String placeOfBirth;

	@Column
	@DateTimeFormat
	private Date createdDate;

	@Column
	@DateTimeFormat
	private Date updatedDate;
}
