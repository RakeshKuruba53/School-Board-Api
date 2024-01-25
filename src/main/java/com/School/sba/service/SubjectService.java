package com.School.sba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.School.sba.entity.Subject;
import com.School.sba.requestdto.SubjectRequest;
import com.School.sba.responsedto.AcademicProgramResponse;
import com.School.sba.responsedto.SubjectResponse;
import com.School.sba.serviceimpl.SubjectServiceImpl;
import com.School.sba.util.ResponseStructure;

public interface SubjectService {
	ResponseEntity<ResponseStructure<AcademicProgramResponse>>  addSubject(int programId, SubjectRequest request);

	ResponseEntity<ResponseStructure<List<Subject>>> findSubjects();

}
