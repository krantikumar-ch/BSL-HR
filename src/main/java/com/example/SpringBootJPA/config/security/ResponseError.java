package com.example.SpringBootJPA.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor 
public class ResponseError {

	private int status;
	
	private String message;
	
	private String description;
}
