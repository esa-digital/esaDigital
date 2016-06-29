package uk.gov.dwp.esa.serviceImpl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import uk.gov.dwp.esa.controllers.ClaimantController;
import uk.gov.dwp.esa.service.TokenValidationService;
import uk.gov.dwp.esa.util.ServiceFacade;

@Service
public class TokenValidationServiceImpl implements TokenValidationService {

	private static final Logger logger = Logger.getLogger(TokenValidationServiceImpl.class);

	// this needs to change to include MessageSource
	private static String TOKEN_SERV_URL;

	@Value("${gateway.keyservice.url:#{null}}")
	private String url;

	@Override
	public HttpStatus getStatus(String token) {

		if (token == null || token.equals("")) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "token not valid");
		}

		RestTemplate restTemplate = new RestTemplate();

		logger.info("THE GATEWAY URL IS " + url);

		if (url != null) {
			TOKEN_SERV_URL = url;
		} else {
			TOKEN_SERV_URL = "http://localhost:8081/api/match/?key=";
		}

		String tokenServiceUrl = TOKEN_SERV_URL + token;
		ResponseEntity<Map> responseEntity = restTemplate.getForEntity(tokenServiceUrl, Map.class);
		if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}

		return responseEntity.getStatusCode();

	}

}
