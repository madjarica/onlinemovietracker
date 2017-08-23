package com.omt.service;

import java.util.List;

import com.omt.JsonResults.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.domain.ScheduleList;
import com.omt.repository.ScheduleListRepository;

@Service
public class ScheduleListService {

	ScheduleListRepository scheduleListRepository;
	Notification notification;

    @Autowired
    public ScheduleListService(ScheduleListRepository scheduleListRepository) {
        this.scheduleListRepository = scheduleListRepository;
    }

    public List<ScheduleList> findAll() {
        // TODO Auto-generated method stub
        return scheduleListRepository.findAll();
    }

    public ScheduleList save(ScheduleList scheduleList) {
        // TODO Auto-generated method stub
        return scheduleListRepository.save(scheduleList);
    }

    public ScheduleList findOne(Long id) {
        // TODO Auto-generated method stub
        return scheduleListRepository.findOne(id);
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        scheduleListRepository.delete(id);
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}

