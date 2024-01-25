package com.School.sba.service;

import org.springframework.http.ResponseEntity;

import com.School.sba.requestdto.ScheduleRequest;
import com.School.sba.responsedto.ScheduleResponse;
import com.School.sba.util.ResponseStructure;

public interface ScheduleService {

	ResponseEntity<ResponseStructure<ScheduleResponse>> saveSchedule(int schoolId, ScheduleRequest scheduleRequest);

	ResponseEntity<ResponseStructure<ScheduleResponse>> findSchedule(int schoolId);

	ResponseEntity<ResponseStructure<ScheduleResponse>> updateSchedule(int scheduleId, ScheduleRequest request);

}
