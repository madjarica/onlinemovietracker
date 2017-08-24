package com.omt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omt.domain.ScheduleList;

import java.util.List;

@Repository
public interface ScheduleListRepository extends JpaRepository<ScheduleList, Long> {
    List<ScheduleList> findByWatchlistId(Long id);
    List<ScheduleList> findByWatchlistWatchlistUser(String username);
}
