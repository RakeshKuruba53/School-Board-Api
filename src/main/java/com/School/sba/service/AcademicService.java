package com.School.sba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.School.sba.requestdto.AcademicProgramRequest;
import com.School.sba.responsedto.AcademicProgramResponse;
import com.School.sba.util.ResponseStructure;

public interface AcademicService {

	ResponseEntity<ResponseStructure<AcademicProgramResponse>> addAcademicPrograms(int schoolId, AcademicProgramRequest request);

	ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>> findAcademicPrograms(int schoolId);

}
