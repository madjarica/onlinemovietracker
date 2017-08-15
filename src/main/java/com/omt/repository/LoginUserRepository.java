package com.omt.repository;

import com.omt.domain.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {

	public LoginUser findByUsername(String username);
	public LoginUser findByCodeForActivation(String code);
	
}
