package com.School.sba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.School.sba.entity.Schedule;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{

}
