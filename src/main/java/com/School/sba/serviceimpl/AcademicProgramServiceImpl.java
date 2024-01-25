package com.School.sba.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.School.sba.entity.AcademicProgram;
import com.School.sba.repository.AcademicProgramRepo;
import com.School.sba.repository.SchoolRepository;
import com.School.sba.requestdto.AcademicProgramRequest;
import com.School.sba.responsedto.AcademicProgramResponse;
import com.School.sba.service.AcademicService;
import com.School.sba.util.ResponseStructure;
@Service
public class AcademicProgramServiceImpl implements AcademicService{
	@Autowired
	AcademicProgramRepo academicProgramRepo;

	@Autowired
	SchoolRepository schoolRepository;

	@Autowired
	ResponseStructure<AcademicProgramResponse> structure;

	public AcademicProgramResponse mapToAcademicProgramResponse(AcademicProgram academicProgram) {

		return AcademicProgramResponse.builder()
				.beginsAt(academicProgram.getBeginsAt())
				.endsAt(academicProgram.getEndsAt())
				.programName(academicProgram.getProgramName())
				.programId(academicProgram.getProgramId())
				.programType(academicProgram.getProgramType())
				.build();

	}

	private AcademicProgram mapToAcademicProgram(AcademicProgramRequest request) {
		return AcademicProgram.builder()
				.beginsAt(request.getBeginsAt())
				.endsAt(request.getEndsAt())
				.programName(request.getProgramName())
				.programType(request.getProgramType())
				.build();
	}

	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> addAcademicPrograms(
			int schoolId,AcademicProgramRequest request) {
		return schoolRepository.findById(schoolId).map(s->{

			AcademicProgram academicProgram = academicProgramRepo.save(mapToAcademicProgram(request));
			academicProgram.setSchool(s);
			schoolRepository.save(s);

			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMesaage("program Added Succesfully");
			structure.setData(mapToAcademicProgramResponse(academicProgram));
			return new  ResponseEntity<ResponseStructure<AcademicProgramResponse>>(structure,HttpStatus.CREATED);

		}).orElseThrow(()->new RuntimeException());

	}

	@Override
	public ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>> findAcademicPrograms(int schoolId) {
		return schoolRepository.findById(schoolId).map(s->{
			List<AcademicProgram> programs = s.getPrograms();
			List<AcademicProgramResponse> programResponses=new ArrayList<AcademicProgramResponse>();
			programs.forEach(program->{
				programResponses.add(mapToAcademicProgramResponse(program));
			});
			ResponseStructure<List<AcademicProgramResponse>> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMesaage("Programs Found Succesfully");
			responseStructure.setData(programResponses);

			return new ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>>(responseStructure,HttpStatus.FOUND);

		}).orElseThrow(()->new RuntimeException());
	}






}
