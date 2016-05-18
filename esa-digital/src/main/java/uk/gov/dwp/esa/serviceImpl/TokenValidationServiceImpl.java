package uk.gov.dwp.esa.serviceImpl;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.gov.dwp.esa.model.TokenServiceRequest;
import uk.gov.dwp.esa.model.TokenServiceResponse;
import uk.gov.dwp.esa.service.TokenValidationService;

public class TokenValidationServiceImpl implements TokenValidationService {

	//this needs to change to include MessageSource
	private static final String TOKEN_SERV_URL = "https://localhost:8082/api/key";
			
	@Override
	public String getStatus(TokenServiceRequest request) {
		
		TokenServiceResponse response = null;
		ObjectMapper mapper = new ObjectMapper();
        try {
        	
		 if (request.getCustomerId() == null || request.getCustomerId().equals("")) {
	            return "";
	        }

	        RestTemplate restTemplate = new RestTemplate();

	        //messageSource.getMessage(TOKEN_SERVICE_URL_PROPERTY, null, Locale.ENGLISH);
	        String jsonOutput = mapper.writeValueAsString(request);
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<String> entity = new HttpEntity<String>(jsonOutput, headers);
	        ResponseEntity<String> responseEntity  =  restTemplate.exchange(TOKEN_SERV_URL,HttpMethod.POST,entity,String.class);
	        if (responseEntity.getStatusCode() == HttpStatus.OK) {
	        	   response = mapper.readValue(responseEntity.getBody(), TokenServiceResponse.class);
	        	} else if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
	        	  throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
	        	}
			} catch (JsonProcessingException e) {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND,e.getMessage());
			} catch (IOException e) {

				throw new HttpClientErrorException(HttpStatus.NOT_FOUND,e.getMessage());
			}
	       
	        
			return response.getStatus();

	}

}
