package com.omt.timerTasks;

import com.omt.domain.LoginUser;
import com.omt.domain.ScheduleList;
import com.omt.domain.UserNotification;
import com.omt.domain.Watchlist;

import com.omt.service.UserNotificationService;
import com.omt.service.UserService;
import org.springframework.stereotype.Component;


import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimerTask;

@Component
public class FirstTimer extends TimerTask {

    UserNotificationService userNotificationService;
    UserService userService;

    public FirstTimer(UserNotificationService userNotificationService, UserService userService) {
        this.userNotificationService = userNotificationService;
        this.userService = userService;
    }

    ScheduleList scheduleList;
    Watchlist watchlist;
    LoginUser user;
    Date date;

    @Override
    public void run() {

        //Notification
        UserNotification userNotification= new UserNotification();
        watchlist = scheduleList.getWatchlist();
        System.out.println(watchlist.getWatchlistUser() + ", you wanted to watch " + watchlist.getVideo().getName() + " at " + date + "........." + LocalDateTime.now());
        userNotification.setRead(false);
        userNotification.setType("reminder");
        userNotification.setReciver(watchlist.getWatchlistUser());
        userNotification.setWatchlist(watchlist);
        userNotification.setCreatedDate(date);
        userNotificationService.save(userNotification);

        //Email notification
        user = userService.findByUsername(watchlist.getWatchlistUser());
        try {
            userNotificationService.sendScheduledReminder(user.getEmail(), user.getUsername(), watchlist.getVideo().getName(), date);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
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
