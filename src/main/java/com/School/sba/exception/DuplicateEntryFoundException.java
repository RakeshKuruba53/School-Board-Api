package com.School.sba.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DuplicateEntryFoundException extends RuntimeException {
	private String message;

}
