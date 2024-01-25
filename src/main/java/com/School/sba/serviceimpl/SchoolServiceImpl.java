package com.School.sba.serviceimpl;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.School.sba.entity.School;
import com.School.sba.enums.UserRole;
import com.School.sba.repository.SchoolRepository;
import com.School.sba.repository.UserRepository;
import com.School.sba.requestdto.SchoolRequest;
import com.School.sba.responsedto.SchoolResponse;
import com.School.sba.service.SchoolService;
import com.School.sba.util.ResponseStructure;

import lombok.Builder;
@Service
public class SchoolServiceImpl implements SchoolService{

	@Autowired
	UserRepository userRepository;
	@Autowired
	SchoolRepository schoolRepository;

	@Autowired
	ResponseStructure<SchoolResponse> structure; 
	
	
	private SchoolResponse mapToSchoolResponse(School school) {
		return SchoolResponse.builder()
				.schooId(school.getSchooId())
				.schoolName(school.getSchoolName())
				.schoolContactNo(school.getSchoolContactNo())
				.schoolEmail(school.getSchoolEmail())
				.build();
	}
	private School mapToSchool(SchoolRequest schoolRequest) {

		return School.builder()
				.schoolEmail(schoolRequest.getSchoolEmail())
				.schoolContactNo(schoolRequest.getSchoolContactNo())
				.schoolName(schoolRequest.getSchoolName())
				.build();
	}

	@Override
	public ResponseEntity<ResponseStructure<SchoolResponse>> saveSchool( SchoolRequest schoolRequest) {
       String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByUserName(username).map(u->{
			if(u.getUserRole().equals(UserRole.ADMIN)) {
				if(u.getSchool()==null) {
					School school=mapToSchool(schoolRequest);
					school=schoolRepository.save(school);//saved the School
					u.setSchool(school);
					userRepository.save(u);//I have Updated the User With New School
					structure.setStatus(HttpStatus.CREATED.value());
					structure.setMesaage("School Saved Succesfully");
					structure.setData(mapToSchoolResponse(school));
					return new ResponseEntity<ResponseStructure<SchoolResponse>>(structure,HttpStatus.CREATED);
				}else 
					throw new RuntimeException();
			}else 
				throw new RuntimeException();

		}).orElseThrow(()->new RuntimeException());
	}
	
	



}
