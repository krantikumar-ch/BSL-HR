package com.example.SpringBootJPA.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class AuthorizedResponse {

	private String authToken;
	
	private String userName;
}
