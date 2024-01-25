package com.School.sba.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.School.sba.entity.AcademicProgram;
import com.School.sba.entity.Subject;
import com.School.sba.entity.User;
import com.School.sba.enums.UserRole;
import com.School.sba.exception.AdminExistException;
import com.School.sba.exception.DuplicateEntryFoundException;
import com.School.sba.repository.AcademicProgramRepo;
import com.School.sba.repository.SubjectRepo;
import com.School.sba.repository.UserRepository;

import com.School.sba.requestdto.UserRequest;
import com.School.sba.responsedto.UserResponse;
import com.School.sba.service.UserService;
import com.School.sba.util.ResponseStructure;
import com.jsp.ums.exception.UserNotFoundByIdException;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	private ResponseStructure<UserResponse> responseStructure;

	@Autowired
	AcademicProgramRepo academicProgramRepo;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	SubjectRepo subjectRepo;


	private User mapToUser(UserRequest userRequest) {
		User user = new User();
		user.setUserRole(userRequest.getUserRole());
		user.setUserName(userRequest.getUserName());
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(encoder.encode(userRequest.getPassword()));
		user.setContactNo(userRequest.getContactNo());
		return user;
	}

	private UserResponse mapToUserResponse(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(user.getUserId());
		userResponse.setUserRole(user.getUserRole());
		userResponse.setUserName(user.getUserName());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setLastName(user.getLastName());
		userResponse.setEmail(user.getEmail());
		userResponse.setContactNo(user.getContactNo());

		return userResponse;
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest) 
	{

		if (userRequest.getUserRole().equals(UserRole.TEACHER)||userRequest.getUserRole().equals(UserRole.STUDENT)) 
		{

			if (userRepository.existsByUserRole(UserRole.ADMIN) == true )
			{
				String name = SecurityContextHolder.getContext().getAuthentication().getName();
				
				
				User user1 = userRepository.findByUserName(name).map(user-> user).orElseThrow(()->new UserNotFoundByIdException("not found"));
				System.out.println(name);
				User user = mapToUser(userRequest);
				user.setSchool(user1.getSchool());
				userRepository.save(user);

				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMesaage("user Details Registered Successfully");
				responseStructure.setData(mapToUserResponse(user));

			} else {
				throw new AdminExistException("");
			}
		} else {
			throw new RuntimeException();
		}
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId)
	{
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundByIdException("user not found by Id"));


		if(user.isDeleted()==true)
		{
			throw new UserNotFoundByIdException("user already deleted");
		}
		user.setUserId(userId);
		user.setDeleted(true);
		userRepository.save(user);	
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMesaage("user sucessfully deleted");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);
	}



	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUniqueUser(int userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundByIdException("user not found with the id " + userId));
		if (user.isDeleted() == true)
			throw new UserNotFoundByIdException("user may be deleted");
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMesaage("user Find sucessfully ");
		responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> assignUsers(int programId, int userId) {

		User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException());
		if(user.getUserRole()!=UserRole.ADMIN) {
			AcademicProgram academicProgram = academicProgramRepo.findById(programId).orElseThrow(()->new RuntimeException());
			academicProgram.getUsers().add(user);
			academicProgramRepo.save(academicProgram);
			user.getAcademicPrograms().add(academicProgram);
			userRepository.save(user);

		}else {
			throw new RuntimeException();
		}
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMesaage("Assigned User to Academic Program");
		responseStructure.setData(mapToUserResponse(user));

		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerAdmin(UserRequest request) {
		User user;
		if(request.getUserRole().equals(UserRole.ADMIN)) {
			if(userRepository.existsByUserRole(request.getUserRole())==false) 
			{
				user = userRepository.save(mapToUser(request));

				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMesaage("Admin Saved Succesfully");
				responseStructure.setData(mapToUserResponse(user));

			}else {
				throw new DuplicateEntryFoundException("Duplicate Admin");
			}
		}
		else {
			throw new AdminExistException("Admin Already presentS");
		}
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> assignSubjectToTeacher(int subjectId, int userId) {
		
		User user2 = userRepository.findById(userId).map(user->user).orElseThrow();
		if(user2.getUserRole().equals(UserRole.TEACHER)) {
			Subject subject2 = subjectRepo.findById(subjectId).map(subject->subject).orElseThrow();
			user2.setSubject(subject2);
			userRepository.save(user2);
			responseStructure.setStatus(HttpStatus.ACCEPTED.value());
			responseStructure.setMesaage("Assigned");
			responseStructure.setData(mapToUserResponse(user2));
			
		}else {
			throw new RuntimeException();
		}
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.ACCEPTED);
	}
}

