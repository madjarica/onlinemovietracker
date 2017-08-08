package com.omt.repository;

import com.omt.domain.AdminMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdminMessageRepository extends JpaRepository<AdminMessage, Long> {

    public List<AdminMessage> findAll();

    public AdminMessage findOne(Long id);

    public AdminMessage save(AdminMessage adminMessage);

    public void delete(Long id);
}
