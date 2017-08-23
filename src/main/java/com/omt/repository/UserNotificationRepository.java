package com.omt.repository;

import com.omt.domain.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long>{

    public List<UserNotification> findAll();

    public UserNotification findOne(Long id);

    public UserNotification save(UserNotification userNotification);

    public void delete(Long id);

    public List<UserNotification> getUserNotificationByWatchlistWatchlistUser(String name);
}
