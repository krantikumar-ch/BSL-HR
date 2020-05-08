package com.example.SpringBootJPA.config.security;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyHttpServletRequest extends HttpServletRequestWrapper {

	private Map<String, String> headerMap = new HashMap<>();
	
	
	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
	}
	
	public void addHeader(String name, String value){
		headerMap.put(name, value);
	}
	
	
	@Override
	public String getHeader(String name) {
		if(headerMap.containsKey(name)){
			return headerMap.get(name);
		}
		return super.getHeader(name);
	}
	
/*
	@RequestHeader annotation will use getHeaders method
	to get header value. If we are use @RequestHeader annotation
	this method should override to get our custom header value
	*/
	@Override
	public Enumeration<String> getHeaders(String name) {
		Set<String> headerValues = new HashSet<>();
	    headerValues.add(headerMap.get(name));

	    Enumeration<String> underlyingHeaderValues = ((HttpServletRequest) getRequest()).getHeaders(name);
	    while (underlyingHeaderValues.hasMoreElements()) {
	      headerValues.add(underlyingHeaderValues.nextElement());
	    }

	    return Collections.enumeration(headerValues);
	}

	
}
