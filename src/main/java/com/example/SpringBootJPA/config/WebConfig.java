package com.example.SpringBootJPA.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private HandlerInterceptor handlerInterceptor;


	/*@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(handlerInterceptor);
	}*/
	
	/*@Override
	public void addCorsMappings(CorsRegistry registry) {
		  registry.addMapping("/**").allowedMethods("*");
	}*/

}
