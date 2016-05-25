package uk.gov.dwp.esa.service;

import org.springframework.http.HttpStatus;

public interface TokenValidationService {
	
	HttpStatus getStatus(String token);

}
