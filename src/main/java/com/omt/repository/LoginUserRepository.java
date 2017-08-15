package com.omt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omt.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByCodeForActivation(String code);
	
}
