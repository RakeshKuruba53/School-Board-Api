package com.School.sba.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.School.sba.entity.School;
import com.School.sba.requestdto.SchoolRequest;
import com.School.sba.responsedto.SchoolResponse;
import com.School.sba.util.ResponseStructure;


public interface SchoolService {

	


	ResponseEntity<ResponseStructure<SchoolResponse>> saveSchool(SchoolRequest schoolRequest);

	

	
	

	

}
