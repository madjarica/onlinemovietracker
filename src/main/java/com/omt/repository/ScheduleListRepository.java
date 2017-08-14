package com.omt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omt.domain.ScheduleList;

@Repository
public interface ScheduleListRepository extends JpaRepository<ScheduleList, Long> {

}
