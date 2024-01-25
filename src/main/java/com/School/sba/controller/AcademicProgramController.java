package com.School.sba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.School.sba.requestdto.AcademicProgramRequest;
import com.School.sba.responsedto.AcademicProgramResponse;
import com.School.sba.service.AcademicService;
import com.School.sba.util.ResponseStructure;

@RestController
public class AcademicProgramController {
	@Autowired
	AcademicService academicProgramService;
	 @PostMapping(value="/schools/{schoolId}/academic-programs")
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> addAcademicPrograms(@PathVariable int schoolId, @RequestBody AcademicProgramRequest request){
		return academicProgramService.addAcademicPrograms(schoolId,request);
	}
	 @GetMapping(value="/schools/{schoolId}")
	 public  ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>> findAcademicPrograms(@PathVariable int schoolId){
		 return academicProgramService.findAcademicPrograms(schoolId);
	 }

}
