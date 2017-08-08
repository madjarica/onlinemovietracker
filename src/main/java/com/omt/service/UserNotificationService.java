package com.omt.service;

import com.omt.domain.UserNotification;
import com.omt.repository.UserNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserNotificationService {

    UserNotificationRepository userNotificationRepository;


    @Autowired
    public UserNotificationService(UserNotificationRepository userNotificationRepository) {
        this.userNotificationRepository = userNotificationRepository;
    }

    public List<UserNotification> findAll() {
        // TODO Auto-generated method stub
        return userNotificationRepository.findAll();
    }

    public UserNotification save(UserNotification userNotification) {
        // TODO Auto-generated method stub
        return userNotificationRepository.save(userNotification);
    }

    public UserNotification findOne(Long id) {
        // TODO Auto-generated method stub
        return userNotificationRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        userNotificationRepository.delete(id);
    }

}
