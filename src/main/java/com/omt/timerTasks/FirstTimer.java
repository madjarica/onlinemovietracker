package com.omt.timerTasks;

import com.omt.JsonResults.Notification;

import com.omt.domain.ScheduleList;
import com.omt.domain.UserNotification;
import com.omt.domain.Watchlist;
import com.omt.service.ScheduleListService;

import com.omt.service.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimerTask;

@Component
public class FirstTimer extends TimerTask {

    UserNotificationService userNotificationService;

    public FirstTimer(UserNotificationService userNotificationService) {
        this.userNotificationService = userNotificationService;
    }

    ScheduleList scheduleList;
    Watchlist watchlist;
    Date date;

    @Override
    public void run() {
        UserNotification userNotification= new UserNotification();
        watchlist = scheduleList.getWatchlist();
        System.out.println(watchlist.getWatchlistUser() + ", you wanted to watch " + watchlist.getVideo().getName() + " at " + date + "........." + LocalDateTime.now());
        userNotification.setRead(false);
        userNotification.setType("Reminder");
        userNotification.setWatchlist(watchlist);
        userNotification.setCreatedDate(date);
        userNotificationService.save(userNotification);
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ScheduleList getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(ScheduleList scheduleList) {
        this.scheduleList = scheduleList;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }
}
