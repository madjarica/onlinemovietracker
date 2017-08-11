package com.omt.domain;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

	private String name;

	Role() {}

	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//	@Enumerated(EnumType.STRING)
//	private RoleType type;
//
//	public RoleType getType() {
//		return type;
//	}
//
//	public void setType(RoleType type) {
//		this.type = type;
//	}
//
//	public enum RoleType {
//		ROLE_USER, ROLE_ADMIN, ROLE_CLIENT, ROLE_TRUSTED_CLIENT
//	}
}
