package uk.gov.dwp.esa.serviceImpl;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import uk.gov.dwp.esa.service.TokenValidationService;

@Service
public class TokenValidationServiceImpl implements TokenValidationService {

	//this needs to change to include MessageSource
	//private static final String TOKEN_SERV_URL = "http://10.32.20.100:8081/api/match/?key=";
	private static final String TOKEN_SERV_URL = "http://192.168.20.102:8081/api/match/?key=";
	//private static final String TOKEN_SERV_URL = "https://192.168.20.102/api/match/?key=";
			
	@Override
	public HttpStatus getStatus(String token) {
        	
		 if (token == null || token.equals("")) {
			 throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"token not valid");
	        }

	        RestTemplate restTemplate = new RestTemplate();

	        String tokenServiceUrl = TOKEN_SERV_URL+token;
	        ResponseEntity<Map> responseEntity  =  restTemplate.getForEntity(tokenServiceUrl, Map.class);
	        if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
	        	  throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
	        }
			
	       	        
			return responseEntity.getStatusCode();

	

}
	
}
