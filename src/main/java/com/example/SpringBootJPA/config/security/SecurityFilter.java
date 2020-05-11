package com.example.SpringBootJPA.config.security;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.SpringBootJPA.exceptions.ResponseError;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author suganya
 *
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest httpReq, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		MyHttpServletRequest request = new MyHttpServletRequest(httpReq);
		String authToken = request.getHeader("authToken");
		Map<String,Object> claims = new HashMap<>();
		
		if(authToken != null){
			try{
				claims = JWTUtils.decodeJWT(authToken);
			}
			catch(Exception e){
				sendErrorResponse(response);
				return;
			}
			
			if(claims.isEmpty()){
			  sendErrorResponse(response);
			  return;
			}
			
			String userName = (String)claims.get("userName");
			request.addHeader("userName", userName);
			request.addHeader("userId", (String)claims.get("userId"));
			SecurityContext ctx = SecurityContextHolder.getContext();
			
			if(userName != null && ctx.getAuthentication() == null){
				
				UserDetails userDetails = userService.loadUserByUsername(userName);
				UsernamePasswordAuthenticationToken token = 
						new UsernamePasswordAuthenticationToken(userDetails, 
								null, userDetails.getAuthorities());
				
				token.setDetails(new WebAuthenticationDetailsSource()
						.buildDetails(request));
				
				ctx.setAuthentication(token);
				
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	
	/*PrepareErrorResponse and send to the client*/
	public void sendErrorResponse(HttpServletResponse response) throws IOException{
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		ResponseError error = new ResponseError(HttpServletResponse.SC_UNAUTHORIZED, 
				"UnAuthorized User / Session Expired", "UnAuthorized User / Session Expired");
		
		String responseString = new ObjectMapper().writeValueAsString(error);
		
		response.setContentLength(responseString.length());
		response.getWriter().write(responseString);
		response.getWriter().flush();
		return;
		
	}

}
