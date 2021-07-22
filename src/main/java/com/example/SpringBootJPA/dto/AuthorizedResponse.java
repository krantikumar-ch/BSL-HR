package com.example.SpringBootJPA.dto;

public class AuthorizedResponse {

	private String authToken;
	
	private String userName;

	public AuthorizedResponse(String authToken, String userName) {
		this.authToken = authToken;
		this.userName = userName;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
