package com.omt.repository;

import com.omt.domain.LoginUser;
import com.omt.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {

	LoginUser findByUsername(String username);
	LoginUser findByCodeForActivation(String code);
	LoginUser findByEmail(String email);
	LoginUser findByPasswordActivationLink(String code);
	List<LoginUser> findByRoles(Role role);
	
}
