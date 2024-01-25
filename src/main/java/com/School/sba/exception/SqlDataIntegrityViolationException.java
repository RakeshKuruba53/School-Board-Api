package com.School.sba.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SqlDataIntegrityViolationException extends RuntimeException {
	private String message;

}
