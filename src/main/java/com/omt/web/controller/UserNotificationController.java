package com.omt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.UserNotification;
import com.omt.service.UserNotificationService;

@RestController
@RequestMapping("/user_notifications")
public class UserNotificationController {

    UserNotificationService userNotificationService;

    @Autowired
    public UserNotificationController(UserNotificationService userNotificationService) {
        this.userNotificationService = userNotificationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserNotification> findAll(){
        return userNotificationService.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public UserNotification findOne(@PathVariable("id") Long id){
        return userNotificationService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserNotification save(@RequestBody UserNotification userNotification){
        return userNotificationService.save(userNotification);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UserNotification update(@RequestBody UserNotification userNotification){
        return userNotificationService.save(userNotification);
    }

    @RequestMapping(path="{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id){
        userNotificationService.delete(id);
    }

    @RequestMapping(path="by-user/{username}", method = RequestMethod.GET)
    public List<UserNotification> getUserNotifications(@PathVariable("username") String name){
        return userNotificationService.getUserNotifications(name);
    }

}
