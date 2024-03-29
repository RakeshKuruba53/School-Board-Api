package com.School.sba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.School.sba.entity.School;
import com.School.sba.requestdto.SchoolRequest;
import com.School.sba.responsedto.SchoolResponse;
import com.School.sba.service.SchoolService;
import com.School.sba.util.ResponseStructure;


@RestController
public class SchoolController {
	@Autowired
	private SchoolService schoolService;

	@PostMapping(value = "/schools")
	public ResponseEntity<ResponseStructure<SchoolResponse>> saveSchool(@RequestBody SchoolRequest schoolRequest) 
	{
		return schoolService.saveSchool(schoolRequest);
	}
	}
	

