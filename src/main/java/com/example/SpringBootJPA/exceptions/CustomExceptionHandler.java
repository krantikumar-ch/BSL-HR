package com.example.SpringBootJPA.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"rawtypes","unchecked"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<Object> handleRecordNotFoundException(Exception ex, WebRequest request){
		//ResponseEntity<Object> respEntity= new ResponseEntity();
		return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request){
		//ResponseEntity<Object> respEntity= new ResponseEntity();
		return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
