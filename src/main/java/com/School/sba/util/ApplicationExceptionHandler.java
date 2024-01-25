package com.School.sba.util;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.mapping.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.School.sba.exception.DuplicateEntryFoundException;
import com.jsp.ums.exception.UserNotFoundByIdException;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	private ResponseEntity<Object> responseStructure(HttpStatus status, String message, Object rootCause )
	{
		return new ResponseEntity<Object>(Map.of("status",status.value(),"message", message,"rootCause",rootCause),status);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			org.springframework.http.HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		java.util.List<ObjectError> allErrors = ex.getAllErrors();
		Map<String, String> map=new HashMap<>();
		allErrors.forEach(error->{
			FieldError fieldError=(FieldError)error;
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		});
		return  responseStructure(HttpStatus.BAD_REQUEST,"failed to save the userdate",map );
	}
	
	@ExceptionHandler(DuplicateEntryFoundException.class)
	public ResponseEntity<Object> handlerOfDuplicateEntryFoundException(DuplicateEntryFoundException ex)
	{
		
		return responseStructure(HttpStatus.NOT_FOUND, ex.getMessage(),"Duplicate entry found ");
		
	}
	@ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<Object>  handlerOfDataNotFoundException(UserNotFoundByIdException ex){
		return responseStructure(HttpStatus.NOT_FOUND, ex.getMessage(), "User Not Found BY ID");
	}
	
	

}
