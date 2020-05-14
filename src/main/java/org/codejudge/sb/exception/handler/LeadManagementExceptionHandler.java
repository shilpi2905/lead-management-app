package org.codejudge.sb.exception.handler;

import org.codejudge.sb.exception.model.CommonException;
import org.codejudge.sb.exception.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class LeadManagementExceptionHandler {

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		Error ex = new Error("failure", exception.getBindingResult().getFieldError().getDefaultMessage());
		log.error("MethodArgumentNotValidException occurred: {}", exception.getBindingResult().getFieldError());
		return new ResponseEntity<Object>(ex, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler({ CommonException.class })
	public ResponseEntity<Object> handleCommonException(CommonException exception) {
		log.error("CommonException occurred: {}", exception.getError().getReason());
		if(exception.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			return new ResponseEntity<Object>("{}", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(exception.getError(), exception.getStatusCode());
	}
	
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
		log.error("Exception occurred: {}", exception.getMessage());
		Error ex = new Error("failure", "Error: Invalid Input");
		return new ResponseEntity<Object>(ex, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
		log.error("Exception occurred: {}", exception.getMessage());
		return new ResponseEntity<Object>("{}", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> handleException(Exception exception) {
		log.error("Exception occurred: {}", exception.getMessage());
		Error ex = new Error("failure", "Unknown Exception Occured, please retry again");
		return new ResponseEntity<Object>(ex, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
