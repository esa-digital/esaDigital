package uk.gov.dwp.esa.serviceImpl;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.gov.dwp.esa.model.Claim;
import uk.gov.dwp.esa.service.ESAClaimService;

@Service
public class ESAClaimServiceImpl implements ESAClaimService{
	
	
	
	private String claimURl = "http://10.32.20.100:8081/api/Submit";

	@Override
	public ResponseEntity<Map> submitClaimDetails(Claim claim) {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "";
		try {
			json = objectMapper.writeValueAsString(claim);
			return callRestfulServiceByPost(claimURl, json);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	private ResponseEntity<Map>  callRestfulServiceByPost(final String url, final String json){
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> response = restTemplate.postForEntity(url, json, Map.class);
		return response;
	}
}
