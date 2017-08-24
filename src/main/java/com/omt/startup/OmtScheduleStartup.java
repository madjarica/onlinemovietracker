package com.omt.startup;

import com.omt.domain.ScheduleList;
import com.omt.service.ScheduleListService;
import com.omt.service.UserNotificationService;
import com.omt.service.UserService;
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
    UserService userService;
    public static List<Timer> timers = new ArrayList<>();

    @Autowired
    public OmtScheduleStartup(ScheduleListService scheduleListService, UserNotificationService userNotificationService, UserService userService) {
        this.scheduleListService = scheduleListService;
        this.userNotificationService = userNotificationService;
        this.userService = userService;

    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<ScheduleList> scheduleLists = scheduleListService.findAll();
        for(int i = 0; i< scheduleLists.size(); i++){
            FirstTimer firstTimer = new FirstTimer(userNotificationService, userService);
            scheduleLists.get(i).setTimer(i);
            firstTimer.setScheduleList(scheduleLists.get(i));
            firstTimer.setDate(scheduleLists.get(i).getScheduledDateTime());
            Timer timer = new Timer();
            timers.add(timer);
            timers.get(i).schedule(firstTimer, firstTimer.getDate());
            System.out.println("Did it");
        }
    }
}
