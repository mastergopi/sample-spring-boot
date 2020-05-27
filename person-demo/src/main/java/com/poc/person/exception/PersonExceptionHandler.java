package com.poc.person.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.poc.person.constants.PersonConstants;
import com.poc.person.model.ErrorDetail;

@ControllerAdvice
public class PersonExceptionHandler{
	
	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<Object> exception(PersonNotFoundException exception) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(), exception.getMessage(), HttpStatus.NOT_FOUND.toString(),
				PersonConstants.SUCCESS);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> methodArgumentexception(MethodArgumentNotValidException response) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(),
				response.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.OK.toString(),
				PersonConstants.VALIDATION_ERROR);
		return new ResponseEntity<>(errorDetails, HttpStatus.OK);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> commonException(Exception exception) {
		ErrorDetail errorDetails = new ErrorDetail(new Date(), exception.getMessage(),
				HttpStatus.EXPECTATION_FAILED.toString(), PersonConstants.FAILURE);
		return new ResponseEntity<>(errorDetails, HttpStatus.EXPECTATION_FAILED);
	}
}
