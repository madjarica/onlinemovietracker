package com.omt.startup;

import com.omt.JsonResults.Notification;
import com.omt.service.ScheduleListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BridgeForNotification {

    @Autowired
    ScheduleListService scheduleListService;

    public BridgeForNotification() {
    }

    public void setNotification(Notification notification){
        scheduleListService.setNotification(notification);
    }
}
