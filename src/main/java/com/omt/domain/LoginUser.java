package com.omt.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

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

	@Column
	private String passwordTemp;

	@Column
	private String codeForActivation;

	@Column(nullable = false)
	@NotNull
	private boolean active;
	
	@Column(nullable = false)
	@NotNull
	private boolean status;
	
	@Column
	@DateTimeFormat
	private Date blockedUntil;
	   
	@Column(nullable = false)
	@NotNull
	private boolean subscription;
	   
	@Column(unique = true, nullable = false)
	@NotNull
	private String email;
	   
	@Column
	@DateTimeFormat
	private Date createdDate;
	
	@Column
	@DateTimeFormat
	private Date updatedDate;
	
	@ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


	   
	
	
}
