package uk.gov.dwp.esa.service;

import uk.gov.dwp.esa.model.TokenServiceRequest;

public interface TokenValidationService {
	
	String getStatus(TokenServiceRequest request);

}
