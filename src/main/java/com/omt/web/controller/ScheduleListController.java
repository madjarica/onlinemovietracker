package com.omt.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Timer;

import com.omt.service.UserNotificationService;
import com.omt.timerTasks.FirstTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.domain.ScheduleList;
import com.omt.service.ScheduleListService;

@RestController
@RequestMapping("/scheduleLists")
public class ScheduleListController {

    ScheduleListService scheduleListService;
    UserNotificationService userNotificationService;

    @Autowired
    public ScheduleListController(ScheduleListService scheduleListService, UserNotificationService userNotificationService) {
        this.scheduleListService = scheduleListService;
        this.userNotificationService = userNotificationService;
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
        FirstTimer firstTimer = new FirstTimer(userNotificationService);
        Timer timer = new Timer();
        Date dateToExecute = scheduleList.getScheduledDateTime();
        firstTimer.setDate(dateToExecute);
        firstTimer.setScheduleList(scheduleList);
        timer.schedule(firstTimer, dateToExecute);
        return scheduleListService.save(scheduleList);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ScheduleList update(@RequestBody ScheduleList scheduleList) {
        return scheduleListService.save(scheduleList);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        scheduleListService.delete(id);
    }

}
