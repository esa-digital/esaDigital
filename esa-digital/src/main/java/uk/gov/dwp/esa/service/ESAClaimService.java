package uk.gov.dwp.esa.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import uk.gov.dwp.esa.model.Claim;

public interface ESAClaimService {
	
	public ResponseEntity<Map> submitClaimDetails(Claim claim);
	

}
