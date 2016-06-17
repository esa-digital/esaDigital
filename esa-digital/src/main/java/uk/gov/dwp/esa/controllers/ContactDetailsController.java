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

import uk.gov.dwp.esa.model.ContactDetails;
import uk.gov.dwp.esa.util.ControllerUrls;
import uk.gov.dwp.esa.validators.ContactDetailsValidator;

@Controller
public class ContactDetailsController {

	private static final Logger logger = LogManager.getLogger(ContactDetailsController.class);
	protected static final String CONTACT_DETAILS = "ContactDetails";
	private String NEXT_FORM = "/api" + ControllerUrls.ALTERNATIVE_FORMATS;
	private static String CONTACT_DETAILS_FORM = "contact-details";
	
		
	@Autowired
	private ContactDetailsValidator contactDetailsValidator;
	
		
	@RequestMapping(value = ControllerUrls.CONTACT_DETAILS, method = RequestMethod.GET)
	public String getcontactDetailsForm(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		
        logger.debug(sessionId + " Getting personal details form");
        
        ContactDetails contactDetails = (ContactDetails) session.getAttribute(CONTACT_DETAILS);
        if(contactDetails==null){
        	 contactDetails=  new ContactDetails();
        }else{
        	model.addAttribute(CONTACT_DETAILS,contactDetails);
        }
        
		return CONTACT_DETAILS_FORM;
	}
	
	@RequestMapping(value = ControllerUrls.CONTACT_DETAILS, method = RequestMethod.POST)
	public String saveContactDetailsData(Model model,ContactDetails contactDetails, BindingResult error,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		
		logger.debug(sessionId + "Saving Claimant Details");
		
		  if(error.hasErrors()){
			  //this will check whether there are any preload errors
			  //do something -- return to error page
			  //currently returning the same page, but should redirect to error page.
			  return CONTACT_DETAILS_FORM;
		  }
		  
		  contactDetailsValidator.validate(contactDetails, error);
		  
		  if(error.hasErrors()){
			  model.addAttribute(CONTACT_DETAILS, contactDetails);
			  logger.debug(error);
			  return CONTACT_DETAILS_FORM;
		  }
		  session.setAttribute(CONTACT_DETAILS, contactDetails);
		  
		  logger.debug(sessionId + "Saved Claimant Details");
		return "redirect:" + NEXT_FORM;
		
	}
}
