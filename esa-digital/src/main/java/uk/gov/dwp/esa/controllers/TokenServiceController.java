package uk.gov.dwp.esa.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.gov.dwp.esa.service.TokenValidationService;

@Controller
public class TokenServiceController {
	
	@Autowired
	TokenValidationService service;
	
	//this will the pagw where we send the token from session
	private static final String TOKEN_SERVICE = "/dummy";
	private static final Logger logger = LogManager.getLogger(TokenServiceController.class);

	private static final String TOKEN_REQUEST_PARAMETER = "key";

	private static final String TOKEN_SESSION_ATTRIBUTE = "token";
	
	@RequestMapping(value = TOKEN_SERVICE, method = RequestMethod.GET)
	public String getTokenService(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        logger.debug(sessionId + " Returning disclaimer form");

        // Retrieve the one-time token from the request
        String tokenValue = request.getParameter(TOKEN_REQUEST_PARAMETER);
        logger.debug(sessionId + " Token provided : " + tokenValue);

        // Store the one-time token in the session for checking later
        if (null != tokenValue) {
        	tokenValue = sessionId+":"+tokenValue;
            request.getSession().setAttribute(TOKEN_SESSION_ATTRIBUTE, tokenValue);
        }

		return TOKEN_SERVICE;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex(HttpServletRequest request) {
       

		return "index";
	}
	
	@RequestMapping(value = TOKEN_SERVICE, method = RequestMethod.POST)
	public String validateToken(HttpServletRequest request){
		
		HttpSession session = request.getSession(false);
		String sessionId = session.getId();
		
		  logger.debug(sessionId + " Getting DUMMY form");
		  String sessionAttribute = (String) session.getAttribute(TOKEN_SESSION_ATTRIBUTE);
		  String[] tokenList = sessionAttribute.split(":");
		  if(!tokenList[0].equals("") && tokenList[0].equals(sessionId)){
			  HttpStatus status= service.getStatus(tokenList[1]);
					  //service.getStatus("MADHA7K6J5");
				if(status == HttpStatus.OK){
					return "/personal-details";
				}
		  }else{
			  logger.debug(sessionId + " invalid");
		  }

		//here we should return null
		return null;
		
	}

}
