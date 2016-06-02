package uk.gov.dwp.esa.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken{
	
	private String token;
	

	public CustomAuthenticationToken(String token) {
		super(null, null);
		this.token = token;
	}

	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token= token;
	}

	

}
