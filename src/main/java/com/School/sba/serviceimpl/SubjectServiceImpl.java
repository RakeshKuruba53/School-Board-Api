package com.School.sba.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.School.sba.entity.AcademicProgram;
import com.School.sba.entity.Subject;
import com.School.sba.repository.AcademicProgramRepo;
import com.School.sba.repository.SubjectRepo;
import com.School.sba.requestdto.SubjectRequest;
import com.School.sba.responsedto.AcademicProgramResponse;
import com.School.sba.responsedto.SubjectResponse;
import com.School.sba.service.SubjectService;
import com.School.sba.util.ResponseStructure;
@Service
public class SubjectServiceImpl implements SubjectService{
	@Autowired
	AcademicProgramRepo academicProgramRepo;
	
	@Autowired
	SubjectRepo  subjectRepo;
	
	
	@Autowired
	ResponseStructure<AcademicProgramResponse> responseStructure;
	
	@Autowired
	AcademicProgramServiceImpl academicProgramServiceImpl;

	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> addSubject(int programId, SubjectRequest request) {
		return academicProgramRepo.findById(programId).map(program->{
			
			List<Subject> subjects=(program.getSubjects()!=null)? program.getSubjects():new ArrayList<>();
			
			//to add new Subjects
			request.getSubjects().forEach(name->{
				boolean ispresent=false;
				for(Subject subject:subjects) {
					ispresent=(name.equalsIgnoreCase(subject.getSubjectName())) ? true : false;
					if(ispresent)
						break;
				}
				if(!ispresent)subjects.add(subjectRepo.findBySubjectName(name)
						.orElseGet(()->subjectRepo.save(Subject.builder().subjectName(name).subjectId(1)
								.build())));
			});
			//to remove subjects that are not specified by the clients
			List<Subject> toBeRemoved =new ArrayList<>();
			subjects.forEach(subject->{
				boolean isPresent=false;
				for(String name:request.getSubjects()) {
					isPresent=(subject.getSubjectName().equalsIgnoreCase(name))?true:false;
					if(!isPresent)  break;
				}
				if(!isPresent)toBeRemoved.add(subject);
			});
			subjects.removeAll(toBeRemoved);
			program.setSubjects(subjects);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMesaage("Updated the Subject list to the Academic program");
			responseStructure.setData(academicProgramServiceImpl.mapToAcademicProgramResponse(program));
			return new ResponseEntity<ResponseStructure<AcademicProgramResponse>>(responseStructure,HttpStatus.CREATED);
		}).orElseThrow();
			
			
			
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Subject>>> findSubjects() {
		List<Subject> subjects = subjectRepo.findAll();
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMesaage("Subjects Find Succesfully");
		for(Subject s:subjects) {
			responseStructure.setData(SubjectResponse.bui);
		}
		return new ResponseEntity<ResponseStructure<List<Subject>>>()
		
	}

}
