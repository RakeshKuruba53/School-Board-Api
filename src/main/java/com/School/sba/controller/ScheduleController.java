package com.School.sba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.School.sba.requestdto.ScheduleRequest;
import com.School.sba.responsedto.ScheduleResponse;
import com.School.sba.service.ScheduleService;
import com.School.sba.util.ResponseStructure;

@RestController
public class ScheduleController {
	@Autowired
	ScheduleService scheduleService;
	@PostMapping(value="/schools/{schoolId}")
	public ResponseEntity<ResponseStructure<ScheduleResponse>> saveSchedule(@PathVariable int schoolId, @RequestBody ScheduleRequest scheduleRequest){
		return scheduleService.saveSchedule(schoolId,scheduleRequest);
	}
	@GetMapping(value="/schools/{schoolId}/schools")
	public  ResponseEntity<ResponseStructure<ScheduleResponse>> findSchedule(@PathVariable int schoolId){
		return scheduleService.findSchedule(schoolId);
	}
	@PutMapping(value="/schools/{scheduleId}")
	public ResponseEntity<ResponseStructure<ScheduleResponse>> updateSchedule(@PathVariable int scheduleId,@RequestBody ScheduleRequest request){
		return 	scheduleService.updateSchedule(scheduleId,request);
	}
}
