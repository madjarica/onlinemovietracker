package com.omt.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Timer;

import com.omt.service.UserNotificationService;
import com.omt.service.UserService;
import com.omt.timerTasks.FirstTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.startup.OmtScheduleStartup;
import com.omt.domain.ScheduleList;
import com.omt.service.ScheduleListService;

@RestController
@RequestMapping("/schedule-list")
public class ScheduleListController {

    ScheduleListService scheduleListService;
    UserNotificationService userNotificationService;
    UserService userService;

    @Autowired
    public ScheduleListController(ScheduleListService scheduleListService, UserNotificationService userNotificationService, UserService userService) {
        this.scheduleListService = scheduleListService;
        this.userNotificationService = userNotificationService;
        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<ScheduleList> findAll() {
        return scheduleListService.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ScheduleList findOne(@PathVariable("id") Long id) {
        return scheduleListService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ScheduleList save(@RequestBody ScheduleList scheduleList) {

        FirstTimer firstTimer = new FirstTimer(userNotificationService, userService);
        firstTimer.setScheduleList(scheduleList);
        firstTimer.setDate(scheduleList.getScheduledDateTime());
        scheduleList.setTimer(OmtScheduleStartup.timers.size());

        Timer timer = new Timer();
        OmtScheduleStartup.timers.add(timer);
        OmtScheduleStartup.timers.get(scheduleList.getTimer()).schedule(firstTimer, firstTimer.getDate());

        return scheduleListService.save(scheduleList);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ScheduleList update(@RequestBody ScheduleList scheduleList) {

        FirstTimer firstTimer = new FirstTimer(userNotificationService, userService);
        firstTimer.setScheduleList(scheduleList);
        firstTimer.setDate(scheduleList.getScheduledDateTime());

        Timer timer = new Timer();
        OmtScheduleStartup.timers.get(scheduleList.getTimer()).cancel();
        OmtScheduleStartup.timers.set(scheduleList.getTimer(), timer);
        OmtScheduleStartup.timers.get(scheduleList.getTimer()).schedule(firstTimer, firstTimer.getDate());

        return scheduleListService.save(scheduleList);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        ScheduleList scheduleList = scheduleListService.findOne(id);
        OmtScheduleStartup.timers.get(scheduleList.getTimer()).cancel();
        OmtScheduleStartup.timers.set(scheduleList.getTimer(), null);
        scheduleListService.delete(id);
    }

    @RequestMapping(path = "/by-user/{username}", method = RequestMethod.GET)
    public List<ScheduleList> findByUser(@PathVariable("username") String username){
        return scheduleListService.findByUser(username);
    }

}
