package com.omt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "schedulelists")
public class ScheduleList extends BaseEntity {

	@Column
    @DateTimeFormat
	private Date sheduledDateTime;
	
	@Column
    @DateTimeFormat
	private Date createdDate;
	
	@Column
    @DateTimeFormat
	private Date updatedDate;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public ScheduleList(Date sheduledDateTime, Date createdDate, Date updatedDate, User user) {
		super();
		this.sheduledDateTime = sheduledDateTime;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.user = user;
	}

	public Date getSheduledDateTime() {
		return sheduledDateTime;
	}

	public void setSheduledDateTime(Date sheduledDateTime) {
		this.sheduledDateTime = sheduledDateTime;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}