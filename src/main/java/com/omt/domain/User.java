package com.omt.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Column(unique = true, nullable = false)
    @NotNull
	private String username;

	@Column(nullable = false)
	@NotNull
	private String password;

//	@ManyToMany
//	@JoinTable(joinColumns = @JoinColumn(name = "user_id"),
//	inverseJoinColumns = @JoinColumn(name = "role_id"))
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Role> roles;

	@Column
	private String passwordTemp;

	@Column
	private String codeForActivation;

	@Column
	private boolean active;
	
	@Column
	private boolean status;
	
	@Column
	@DateTimeFormat
	private Date blockedUntil;
	   
	@Column
	private boolean subscription;
	   
	@Column
	private String email;
	   
	@Column
	@DateTimeFormat
	private Date createdDate;
	
	@Column
	@DateTimeFormat
	private Date updatedDate;

	public User() {}

	public User(String username, String password, List<Role> roles, String passwordTemp, String codeForActivation, boolean active, boolean status, Date blockedUntil, boolean subscription, String email, Date createdDate, Date updatedDate) {
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.passwordTemp = passwordTemp;
		this.codeForActivation = codeForActivation;
		this.active = active;
		this.status = status;
		this.blockedUntil = blockedUntil;
		this.subscription = subscription;
		this.email = email;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public User(String username, String password, List<Role> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getPasswordTemp() {
		return passwordTemp;
	}

	public void setPasswordTemp(String passwordTemp) {
		this.passwordTemp = passwordTemp;
	}

	public String getCodeForActivation() {
		return codeForActivation;
	}

	public void setCodeForActivation(String codeForActivation) {
		this.codeForActivation = codeForActivation;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getBlockedUntil() {
		return blockedUntil;
	}

	public void setBlockedUntil(Date blockedUntil) {
		this.blockedUntil = blockedUntil;
	}

	public boolean isSubscription() {
		return subscription;
	}

	public void setSubscription(boolean subscription) {
		this.subscription = subscription;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
}
