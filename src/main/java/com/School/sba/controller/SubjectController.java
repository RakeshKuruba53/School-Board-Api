package com.School.sba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.School.sba.entity.Subject;
import com.School.sba.requestdto.SubjectRequest;
import com.School.sba.responsedto.AcademicProgramResponse;
import com.School.sba.responsedto.SubjectResponse;
import com.School.sba.service.SubjectService;
import com.School.sba.util.ResponseStructure;

@RestController
public class SubjectController {
	@Autowired
	private SubjectService subjectService;
	
	@PostMapping(value = "/academic-programs/{programId}")
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>>  addSubject(@PathVariable int programId,@RequestBody SubjectRequest request){
	return subjectService.addSubject(programId,request);

}
	@GetMapping(value = "/subjects")
	public  ResponseEntity<ResponseStructure<List<Subject>>> findSubjects() {
		return subjectService.findSubjects();
		
	}
}
