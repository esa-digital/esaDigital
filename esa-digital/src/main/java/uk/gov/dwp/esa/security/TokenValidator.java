package uk.gov.dwp.esa.security;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import uk.gov.dwp.esa.service.TokenValidationService;


@Component
public class TokenValidator {
	
	@Autowired
	private TokenValidationService tokenValidationService;
	

    public boolean validate(String token){
    	String[] tokenList = token.split(":");
    	
    	boolean status = false;
    	
			HttpStatus keyStatus= tokenValidationService.getStatus(tokenList[1]);
			if(keyStatus == HttpStatus.OK){
				status = true;
			}
    	
    	return status;
    }
    public UserDetails getUserFromToken(String token){
    	String[] tokenList = token.split(":");
    	User user = new User(tokenList[0], tokenList[1], new HashSet<GrantedAuthority>());
    	return user;
    }
	
    
}