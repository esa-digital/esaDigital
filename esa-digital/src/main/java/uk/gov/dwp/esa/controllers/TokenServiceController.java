package uk.gov.dwp.esa.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.gov.dwp.esa.service.TokenValidationService;

@Controller
public class TokenServiceController {
	
	@Autowired
	TokenValidationService service;
	
	private static final String TOKEN_SERVICE = "/dummy";
	private static final Logger logger = LogManager.getLogger(TokenServiceController.class);
	
	@RequestMapping(value = TOKEN_SERVICE, method = RequestMethod.GET)
	public String getTokenService(Model model, HttpServletRequest request) {
		

		return TOKEN_SERVICE;
	}
	
	@RequestMapping(value = TOKEN_SERVICE, method = RequestMethod.POST)
	public String validateToken(HttpServletRequest request){
		
		  String sessionId = request.getSession().getId();
		  logger.debug(sessionId + " Getting DUMMY form");
//		  
//		  if(error.hasErrors()){
//			  //this will check whether there are any preload errors
//			  //do something -- return to error page
//			  
//			  return TOKEN_SERVICE;
//		  }

		  service.getStatus("MADHA7K6J5");
		
		return null;
		
	}

}
