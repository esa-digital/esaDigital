package uk.gov.dwp.esa.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.gov.dwp.esa.model.Claimant;
import uk.gov.dwp.esa.service.ESAClaimService;
import uk.gov.dwp.esa.validators.ClaimantValidator;

@Controller
public class ClaimantController {
	private static final String CLAIMANT = "Claimant";
	private static final String PERSONAL_DETAILS = "/personal-details";
	private static final Logger logger = LogManager.getLogger(ClaimantController.class);
	
	@Autowired
	private ESAClaimService esaClaimService;
	
	@Autowired
	private ClaimantValidator claimantValidator;
	
		
	@RequestMapping(value = PERSONAL_DETAILS, method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
        logger.debug(sessionId + " Getting personal details form");
        Claimant claimant = (Claimant) session.getAttribute(CLAIMANT);
        if(claimant==null){
        	claimant =  new Claimant();
        }else{
        	model.addAttribute(CLAIMANT,claimant);
        }
        
		return PERSONAL_DETAILS;
	}
	
	@RequestMapping(value = PERSONAL_DETAILS, method = RequestMethod.POST)
	public String saveClaimantData(Model model,Claimant claimant, BindingResult error,
            HttpServletRequest request){
		
		  String token = (String) request.getSession().getAttribute("token");
			String[] tokenList = token.split(":");
		  
		  if(error.hasErrors()){
			  //this will check whether there are any preload errors
			  //do something -- return to error page
			  //currently returning the same page, but should redirect t error page.
			  return PERSONAL_DETAILS;
		  }
		  
		  claimantValidator.validate(claimant, error);
		  if(error.hasErrors()){
			  model.addAttribute(CLAIMANT, claimant);
			  logger.debug(error);
			  return PERSONAL_DETAILS;
		  }
		  	claimant.setToken(tokenList[1]);
	        ResponseEntity<Map> status = esaClaimService.submitClaimDetails(claimant);
	        HttpStatus httpStatus = status.getStatusCode();
	        if(httpStatus.equals(HttpStatus.OK)){
	        	HttpSession session = request.getSession();
		        session.setAttribute(CLAIMANT, claimant);
	        }
		
	        //should redirect to next page.
		return null;
		
	}
}
