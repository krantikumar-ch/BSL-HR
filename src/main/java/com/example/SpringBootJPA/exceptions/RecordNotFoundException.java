package com.example.SpringBootJPA.exceptions;

public class RecordNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8888021312522313087L;

	public RecordNotFoundException(String message) {
		super(message);
	}

	
}
