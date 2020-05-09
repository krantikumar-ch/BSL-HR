package com.example.SpringBootJPA.config.security;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.example.SpringBootJPA.entities.UsersEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {
	
	private static final String SECRET_KEY="treE@532";
	private static final long endTime = 1000*60*30;
	
	public static String createJWT(UsersEntity user){
		
		//The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	    
	  //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	    
	    long nowMillis = System.currentTimeMillis();
	    long expMillis =nowMillis+endTime;
	    System.out.println("start time "+(new Date(nowMillis))
	    		+" -- end time "+(new Date(expMillis)));
	    
	    Map<String, Object> claims = new HashMap<>();
	    claims.put("userId",user.getUserId());
	    claims.put("userName",user.getUserName());
	    claims.put("isAdmin",true);
	    claims.put("domainAccess", Arrays.asList("read","write"));
	    
	    JwtBuilder builder =  Jwts.builder().setId(user.getUserId()+"")
	    		.setClaims(claims).signWith(SignatureAlgorithm.HS256, SECRET_KEY)
	    		.setIssuer(user.getUserName()).setIssuedAt(new Date(nowMillis))
	    		.setExpiration(new Date(expMillis));
	    return builder.compact();

	    
	}
	
	public static final Map<String, Object> decodeJWT(String jwt){
		return extractAllClaims(jwt);
	}
	
	public static final Claims extractAllClaims(String jwt){
		Claims claims = Jwts.parser()		
	            .setSigningKey(SECRET_KEY)
	            .parseClaimsJws(jwt).getBody();
		return claims;
	}
	
	public static final String createActiveToken(Long userId, String userName){
		
		JwtBuilder builder =  Jwts.builder().setId(userId+"")
	    		.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
	    		.setIssuer(userName).setIssuedAt(new Date(System.currentTimeMillis()));
		return builder.compact();
		
	}
	
	
}
