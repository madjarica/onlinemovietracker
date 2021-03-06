package com.omt.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user")
public class LoginUser extends BaseEntity {

	@Column(unique = true, nullable = false)
    @NotNull
	private String username;

	@Column(nullable = false)
	@NotNull
	private String password;

	@Column(nullable = true)
	private String passwordTemp;

	@Column(nullable = true)
	private String passwordActivationLink;

	@Column(nullable = true)
	private String codeForActivation;

	@Column(nullable = false)
	@NotNull
	private boolean active;
	
	@Column(nullable = false)
	@NotNull
	private boolean status;
	
	@Column(nullable = true)
	@DateTimeFormat
	private Date blockedUntil;
	   
	@Column(nullable = false)
	@NotNull
	private boolean subscription;
	   
	@Column(unique = true, nullable = false)
	@NotNull
	private String email;

	@Column(unique = true, nullable = false)
	@NotNull
	private String hashed_email;
	   
	@Column(nullable = true)
	@DateTimeFormat
	private Date createdDate;
	
	@Column(nullable = true)
	@DateTimeFormat
	private Date updatedDate;

	@ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

	public LoginUser() {}

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

	public String getPasswordTemp() {
		return passwordTemp;
	}

	public void setPasswordTemp(String passwordTemp) {
		this.passwordTemp = passwordTemp;
	}

	public String getPasswordActivationLink() {
		return passwordActivationLink;
	}

	public void setPasswordActivationLink(String passwordActivationLink) {
		this.passwordActivationLink = passwordActivationLink;
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

	public String getHashed_email() {
		return hashed_email;
	}

	public void setHashed_email(String hashed_email) {
		this.hashed_email = hashed_email;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
