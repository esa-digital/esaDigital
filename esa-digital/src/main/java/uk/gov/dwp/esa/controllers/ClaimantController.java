package uk.gov.dwp.esa.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.gov.dwp.esa.model.Claimant;
import uk.gov.dwp.esa.util.ControllerUrls;
import uk.gov.dwp.esa.util.GenericModelParser;
import uk.gov.dwp.esa.validators.ClaimantValidator;

@Controller
public class ClaimantController {

	private static final Logger logger = LogManager.getLogger(ClaimantController.class);
	protected static final String CLAIMANT_DETAILS = "Claimant";
	private String NEXT_FORM = "/api" + ControllerUrls.CONTACT_DETAILS;
	private static String PERSONAL_DETAILS_FORM = "personal-details";
	
		
	@Autowired
	private ClaimantValidator claimantValidator;
	
	@Autowired
	private GenericModelParser generator;
	
	//private static final String PROPERTY_FILE="C:\\Users\\e.ratnakar.shetty\\git\\esaDigital\\esa-digital\\src\\main\\resources\\messages.properties";
	private static final String PROPERTY_FILE="C:\\GitRepos\\esaDigital\\esa-digital\\src\\main\\resources\\messages.properties";
	
		
	@RequestMapping(value = ControllerUrls.PERSONAL_DETAILS_FORM, method = RequestMethod.GET)
	public String getPersonalDetailsForm(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		String sessionId = session.getId();
				
		generator.setLocation(PROPERTY_FILE);
		generator.parseModel(model);
		
        logger.debug(sessionId + " Getting personal details form");
        
        Claimant claimant = (Claimant) session.getAttribute(CLAIMANT_DETAILS);
        if(claimant==null){
        	claimant =  new Claimant();
        }else{
        	model.addAttribute(CLAIMANT_DETAILS,claimant);
        }
        
		return PERSONAL_DETAILS_FORM;
	}
	
	@RequestMapping(value = ControllerUrls.PERSONAL_DETAILS_FORM, method = RequestMethod.POST)
	public String saveClaimantData(Model model,Claimant claimant, BindingResult error,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		
		logger.debug(sessionId + "Saving Claimant Details");
		
		  if(error.hasErrors()){
			  //this will check whether there are any preload errors
			  //do something -- return to error page
			  //currently returning the same page, but should redirect to error page.
			  return PERSONAL_DETAILS_FORM;
		  }
		  
		  claimantValidator.validate(claimant, error);
		  
		  if(error.hasErrors()){
			  model.addAttribute(CLAIMANT_DETAILS, claimant);
			  logger.debug(error);
			  return PERSONAL_DETAILS_FORM;
		  }
		  session.setAttribute(CLAIMANT_DETAILS, claimant);
		  
		  logger.debug(sessionId + "Saved Claimant Details");
		return "redirect:" + NEXT_FORM;
		
	}
}
