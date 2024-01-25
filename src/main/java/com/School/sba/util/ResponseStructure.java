package com.School.sba.util;

import org.springframework.stereotype.Component;

import com.School.sba.responsedto.AcademicProgramResponse;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ResponseStructure<T> {
	private int status;
	private String mesaage;
	private T data;
	
	

}
