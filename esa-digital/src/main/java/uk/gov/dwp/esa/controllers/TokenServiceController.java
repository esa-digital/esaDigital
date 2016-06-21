package uk.gov.dwp.esa.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

	protected static final String TOKEN_SESSION_ATTRIBUTE = "token";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getTokenService(HttpServletRequest request) {
		//check if token is already there 
		//check the session id with the session id in the token
		//if token is alreay there then check the page completed in the array.
		//redirect to the last completed page
		//else check f session is new, then 
		HttpSession session = request.getSession(false);
		//String sessionToken = (String) session.getAttribute(TOKEN_SESSION_ATTRIBUTE); 
		if(session!=null){
			String sessionId = session.getId();
			String sessionToken = (String) session.getAttribute(TOKEN_SESSION_ATTRIBUTE); 
			if(sessionToken!=null){
				//error page
				//redirect to 
				 String[] tokens = sessionToken.split(":");
				 if(sessionId.equals(tokens[1])){
					 //check array and redirect to that pageR
					 return "/personal-details";
				 }
			}
		}
		
		return TOKEN_SERVICE;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex(HttpServletRequest request) {
		return "index";
	}
	
	@RequestMapping(value = TOKEN_SERVICE, method = RequestMethod.POST)
	public String validateToken(HttpServletRequest request,String token){
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		if(session!=null && token!=null &&!token.isEmpty()){
			 logger.debug(sessionId + " Getting DUMMY form");
			 	String tokenValue = sessionId+":"+token;
			 	session.setAttribute(TOKEN_SESSION_ATTRIBUTE, tokenValue); 
	            return "redirect:/api/personal-details";
	            //this should chnage to start your application
		}
		 
		return null;
		//this should return error 
		
	}

}
