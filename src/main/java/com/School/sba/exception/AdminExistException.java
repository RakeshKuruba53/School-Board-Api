package com.School.sba.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AdminExistException extends RuntimeException{
	private String message;
	

}
