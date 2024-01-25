package com.School.sba.service;

import org.springframework.http.ResponseEntity;

import com.School.sba.enums.UserRole;
import com.School.sba.requestdto.UserRequest;
import com.School.sba.responsedto.UserResponse;
import com.School.sba.util.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest);

	ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId);

	ResponseEntity<ResponseStructure<UserResponse>> findUniqueUser(int userId);

	ResponseEntity<ResponseStructure<UserResponse>> assignUsers(int programId, int userId);

	ResponseEntity<ResponseStructure<UserResponse>> registerAdmin(UserRequest request);

	ResponseEntity<ResponseStructure<UserResponse>> assignSubjectToTeacher(int subjectId, int userId);

	

}
