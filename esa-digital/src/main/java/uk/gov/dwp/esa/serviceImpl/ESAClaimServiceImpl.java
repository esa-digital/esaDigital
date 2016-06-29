package uk.gov.dwp.esa.serviceImpl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
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
	
	private static final Logger logger = Logger.getLogger(ESAClaimServiceImpl.class);
	
	private String claimURl;
	
	@Value("${gateway.claimservice.url:#{null}}")
	private String url;

	@Override
	public ResponseEntity<Map> submitClaimDetails(Claim claim) {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "";
		try {
			logger.info("THE GATEWAY URL IS " + url);

			if (url != null) {
				claimURl = url;
			} else {
				claimURl = "http://localhost:8081/api/Submit";
			}
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
