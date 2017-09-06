package com.omt.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.omt.config.LoginUserService;
import com.omt.domain.*;
import com.omt.service.AdminMessageService;
import com.omt.service.ScheduleListService;
import com.omt.service.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.omt.service.WatchlistService;

@RestController
@RequestMapping("/watchlists")
public class WatchlistController {

    WatchlistService watchlistService;
    ScheduleListService scheduleListService;
    UserNotificationService userNotificationService;
    LoginUserService loginUserService;
    AdminMessageService adminMessageService;

    @Autowired
    public WatchlistController(WatchlistService watchlistService, ScheduleListService scheduleListService, UserNotificationService userNotificationService, LoginUserService loginUserService, AdminMessageService adminMessageService) {
        this.watchlistService = watchlistService;
        this.scheduleListService = scheduleListService;
        this.userNotificationService = userNotificationService;
        this.loginUserService = loginUserService;
        this.adminMessageService = adminMessageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Watchlist> findAll() {
        return watchlistService.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Watchlist findOne(@PathVariable("id") Long id) {
        return watchlistService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Watchlist save(@RequestBody Watchlist watchlist) throws Exception{
        String username = watchlist.getWatchlistUser();
        Long id = watchlist.getVideo().getId();
        if(checkForDuplicate(username, id) != null) throw new Exception("You already have this in your watchlist");
        return watchlistService.save(watchlist);
    }

    @RequestMapping(path = "change-watch-date/{id}", method = RequestMethod.POST)
    public void changeWatchDate(@PathVariable Long id, @RequestBody Date watchDate) {
        Watchlist watchlist = watchlistService.findOne((long) id);
        if(watchlist != null) {
            watchlist.setWatchDate(watchDate);
            watchlistService.save(watchlist);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Watchlist update(@RequestBody Watchlist watchlist) {
        return watchlistService.save(watchlist);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        List<ScheduleList> scheduleLists =  scheduleListService.findByWatchlistId(id);
        for (ScheduleList scheduleList : scheduleLists) {
            scheduleListService.delete(scheduleList.getId());
        }
        List<UserNotification> userNotifications = userNotificationService.getUserNotificationByWatchlistId(id);
        for (UserNotification userNotification : userNotifications) {
            userNotificationService.delete(userNotification.getId());
        }
        List<AdminMessage> adminMessages = new ArrayList<>();
        adminMessages = adminMessageService.findByWatchlistId(id);
        for (AdminMessage adminMessage:adminMessages) {
            adminMessageService.delete(adminMessage.getId());
        }
        watchlistService.delete(id);
    }

    @RequestMapping(path = "get-user-watchlist/{username}")
    public List<Watchlist> findByUsername(@PathVariable("username") String username) {
        return watchlistService.findByUsername(username);
    }

    @RequestMapping(path = "get-by-title/{search}")
    public List<Watchlist> findByTitle(@PathVariable("search") String search){
        System.out.println(loginUserService.getCurrentUser().getUsername());
        return watchlistService.findByTitle(search, loginUserService.getCurrentUser().getUsername());
    }

    public Watchlist checkForDuplicate(String username, Long id){
        return watchlistService.checkForDuplicate(username, id);
    }

    @RequestMapping(path = "get-for-redirect/{id}")
    public Watchlist findByIdAndUsername(@PathVariable("id") Long id){
    	return watchlistService.findByIdAndUsername(id, loginUserService.getCurrentUser().getUsername());
	}

    @RequestMapping(path = "get-ratings/{id}", method = RequestMethod.GET)
    public Float getAverageRating(@PathVariable("id") Long id){
        return watchlistService.averageRating(id);
    }

    @RequestMapping(path="get-latest-three", method= RequestMethod.GET)
    public List<Watchlist> getLatestThree() {
        List<Watchlist> watchlists = watchlistService.findAll();
        List<Watchlist> latestThree = watchlists.subList(Math.max(watchlists.size() - 3, 0), watchlists.size());
        return latestThree;
    }

}
