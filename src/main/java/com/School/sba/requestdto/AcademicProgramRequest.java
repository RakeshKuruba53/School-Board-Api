package com.School.sba.requestdto;

import java.time.LocalTime;

import com.School.sba.enums.ProgramType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AcademicProgramRequest {
	 private String programName;
	  private LocalTime beginsAt;
	  private LocalTime endsAt;
	  private ProgramType programType;

}
