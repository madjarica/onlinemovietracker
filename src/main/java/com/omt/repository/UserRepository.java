package com.omt.repository;

import com.omt.domain.OmtUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<OmtUser, Long>{
    public OmtUser findByUsername(String username);
}
