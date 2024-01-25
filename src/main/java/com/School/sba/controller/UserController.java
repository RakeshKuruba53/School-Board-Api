package com.School.sba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.School.sba.enums.UserRole;
import com.School.sba.requestdto.UserRequest;
import com.School.sba.responsedto.UserResponse;
import com.School.sba.service.SchoolService;
import com.School.sba.service.UserService;
import com.School.sba.util.ResponseStructure;
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/users")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody UserRequest userRequest)
	{
		return userService.registerUser(userRequest);
			
	}
	@PostMapping(value = "/users/register")
public ResponseEntity<ResponseStructure<UserResponse>> registerAdmin(@RequestBody UserRequest request){
	return userService.registerAdmin(request);
}
	
	@DeleteMapping(value="/{userId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<ResponseStructure<UserResponse>> deletUser(@PathVariable int userId)
	{
		return userService.deleteUser(userId);
	}
	@PreAuthorize("hasAuthority('STUDENT') OR hasAuthority('TEACHER') OR hasAuthority('TEACHER')")
	@GetMapping(value="/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUniqueUser(@PathVariable int userId)
	{
		return userService.findUniqueUser(userId);
	}
	@PutMapping(value = "/academic-programs/{programId}/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> assignUsers(@PathVariable int programId ,@PathVariable int  userId){
		return userService.assignUsers(programId,userId);
		
	}
	@PostMapping(value = "/subjects/{subjectId}/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> assignSubjectToTeacher(@PathVariable int subjectId,@PathVariable int userId){
		return userService.assignSubjectToTeacher(subjectId,userId);
	}
	
}
