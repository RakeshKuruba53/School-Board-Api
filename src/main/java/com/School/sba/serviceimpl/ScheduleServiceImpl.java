package com.School.sba.serviceimpl;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.School.sba.entity.Schedule;
import com.School.sba.entity.School;
import com.School.sba.repository.ScheduleRepository;
import com.School.sba.repository.SchoolRepository;
import com.School.sba.requestdto.ScheduleRequest;
import com.School.sba.responsedto.ScheduleResponse;
import com.School.sba.responsedto.SchoolResponse;
import com.School.sba.service.ScheduleService;
import com.School.sba.util.ResponseStructure;
@Service
public class ScheduleServiceImpl implements ScheduleService{
	@Autowired
	ScheduleRepository scheduleRepository;
	@Autowired
	SchoolRepository schoolRepository;
	@Autowired
	ResponseStructure<ScheduleResponse> responseStructure;
	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> saveSchedule(int schoolId,
			ScheduleRequest scheduleRequest) {
		return	schoolRepository.findById(schoolId).map(s->{
			if(s.getSchedule()==null) {
				Schedule schedule = mapToSchedule(scheduleRequest);
				schedule.setSchool(s);
				schedule=scheduleRepository.save(schedule);
				scheduleRepository.save(schedule);
				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMesaage("Schedule Saved Succesfully");
				responseStructure.setData(mapToScheduleResponse(schedule));
				return new ResponseEntity<ResponseStructure<ScheduleResponse>>(responseStructure,HttpStatus.CREATED);
			}else
				throw new RuntimeException();
		}).orElseThrow(()->new RuntimeException());
	}

	private ScheduleResponse mapToScheduleResponse(Schedule schedule) {

		return ScheduleResponse.builder()
				.breakLengthInMinutes((int) schedule.getBreakLengthInMinutes().toMinutes())
				.breakTime(schedule.getBreakTime())
				.classHourLengthInMinutes((int)schedule.getClassHourLengthInMinutes().toMinutes())
				.lunchTime(schedule.getLunchTime())
				.lunchLengthInMinutes((int)schedule.getLunchLengthInMinutes().toMinutes())
				.opensAt(schedule.getOpensAt())
				.closesAt(schedule.getClosesAt())
				.scheduleId(schedule.getScheduleId())
				.classHoursPerDay((int)schedule.getClassHoursPerDay().toMinutes() )
				.build();

	}
	private Schedule mapToSchedule(ScheduleRequest scheduleRequest) {


		return Schedule.builder()
				.breakLengthInMinutes(Duration.ofMinutes(scheduleRequest.getBreakLengthInMinutes()))
				.breakTime(scheduleRequest.getBreakTime())
				.classHourLengthInMinutes(Duration.ofMinutes(scheduleRequest.getClassHourLengthInMinutes()))
				.classHoursPerDay(Duration.ofMinutes(scheduleRequest.getClassHoursPerDay()))
				.closesAt(scheduleRequest.getClosesAt())
				.opensAt(scheduleRequest.getOpensAt())
				.lunchLengthInMinutes(Duration.ofMinutes(scheduleRequest.getLunchLengthInMinutes()))
				.lunchTime(scheduleRequest.getLunchTime())
				.build();
	}

	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> findSchedule(int schoolId) {


		School school = schoolRepository.findById(schoolId).orElseThrow(()->new IllegalArgumentException("id not found"));
		Schedule schedule = school.getSchedule();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMesaage("Schedule Found Successfully");
		responseStructure.setData(mapToScheduleResponse(schedule));





		return new ResponseEntity<ResponseStructure<ScheduleResponse>>(responseStructure,HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> updateSchedule(int scheduleId,ScheduleRequest request) {
	 return scheduleRepository.findById(scheduleId).map(s->{
			
		Schedule schedule = mapToSchedule(request);
		schedule.setScheduleId(scheduleId);
	schedule = scheduleRepository.save(schedule);
		
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMesaage("Updated Successfully");
		responseStructure.setData(mapToScheduleResponse(schedule));
			return new ResponseEntity<ResponseStructure<ScheduleResponse>>(responseStructure,HttpStatus.OK);
		}).orElseThrow(()->new RuntimeException());
	}


}
