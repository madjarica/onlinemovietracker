package com.omt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omt.domain.ScheduleList;
import com.omt.repository.ScheduleListRepository;

@Service
public class ScheduleListService {

	ScheduleListRepository scheduleListRepository;

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
	
}

