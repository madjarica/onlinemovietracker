package com.omt.startup;

import com.omt.domain.ScheduleList;
import com.omt.service.ScheduleListService;
import com.omt.service.UserNotificationService;
import com.omt.timerTasks.FirstTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

@Component
public class OmtScheduleStartup implements ApplicationListener<ApplicationReadyEvent>{

    ScheduleListService scheduleListService;
    UserNotificationService userNotificationService;

    @Autowired
    public OmtScheduleStartup(ScheduleListService scheduleListService, FirstTimer firstTimer, UserNotificationService userNotificationService) {
        this.scheduleListService = scheduleListService;
        this.userNotificationService = userNotificationService;

    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<ScheduleList> scheduleLists = scheduleListService.findAll();
        for(int i = 0; i< scheduleLists.size(); i++){
            FirstTimer firstTimer = new FirstTimer(userNotificationService);
            firstTimer.setScheduleList(scheduleLists.get(i));
            firstTimer.setDate(scheduleLists.get(i).getScheduledDateTime());
            Timer timer = new Timer();
            timer.schedule(firstTimer, firstTimer.getDate());
            System.out.println("Did it");
        }
    }
}
