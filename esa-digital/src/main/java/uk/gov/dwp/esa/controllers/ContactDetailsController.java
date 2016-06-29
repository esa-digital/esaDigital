package uk.gov.dwp.esa.controllers;

import java.io.IOException;
import java.util.Properties;

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

import uk.gov.dwp.esa.constants.PropertyFileEnum;
import uk.gov.dwp.esa.model.ContactDetails;
import uk.gov.dwp.esa.util.ControllerUrls;
import uk.gov.dwp.esa.util.GenericModelParser;
import uk.gov.dwp.esa.validators.ContactDetailsValidator;

@Controller
public class ContactDetailsController {

	private static final Logger logger = LogManager.getLogger(ContactDetailsController.class);
	protected static final String CONTACT_DETAILS = "ContactDetails";
	private String NEXT_FORM = ControllerUrls.ALTERNATIVE_FORMATS_URL;
	private String PREVIOUS_FORM = ControllerUrls.PERSONAL_DETAILS_URL;
	private static String CONTACT_DETAILS_FORM = "contact-details";

	@Autowired
	private ContactDetailsValidator contactDetailsValidator;

	@Autowired
	private GenericModelParser generator;

	@RequestMapping(value = ControllerUrls.CONTACT_DETAILS_FORM, method = RequestMethod.GET)
	public String getcontactDetailsForm(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		String sessionId = session.getId();
		
		Properties envProp = new Properties();
		Properties messageProp = new Properties();
		try {
			envProp.load(ContactDetailsController.class.getClassLoader().getResourceAsStream("environment.property"));
			envProp.load(ContactDetailsController.class.getClassLoader().getResourceAsStream("messages.property"));
			String fromenv = messageProp.getProperty("claimant.field.alpha");
			String frommsg = envProp.getProperty("keyservice.url");
			String frommsg2 = messageProp.getProperty("claimant.field.alpha");

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(null == session.getAttribute(ControllerUrls.LAST_COMPLETED_FORM)){
			logger.error("Direct page hit: Redirecting to default help page");
			return "redirect:" + session.getAttribute(ControllerUrls.DEFAULT_URL);
		}

		if(session.getAttribute(ControllerUrls.LAST_COMPLETED_FORM).equals(PREVIOUS_FORM)){

			generator.setLocation(PropertyFileEnum.CONTACT_DETAILS_PROPERTY.value());
			generator.parseModel(model);

			logger.info(sessionId + " Getting contact details form");

			ContactDetails contactDetails = (ContactDetails) session.getAttribute(CONTACT_DETAILS);
			if(contactDetails==null){
				contactDetails=  new ContactDetails();
			}

			model.addAttribute(contactDetails);

			return CONTACT_DETAILS_FORM;
		}else{
			logger.info("Previous page not completed, redirecting to last completed for,");
			return "redirect:" + session.getAttribute(ControllerUrls.LAST_COMPLETED_FORM);
		}

	}

	@RequestMapping(value = ControllerUrls.CONTACT_DETAILS_FORM, method = RequestMethod.POST)
	public String saveContactDetailsData(Model model,ContactDetails contactDetails, BindingResult error,HttpServletRequest request){

		HttpSession session = request.getSession(false);
		String sessionId = session.getId();

		logger.debug(sessionId + "Saving Contact Details");

		if(error.hasErrors()){
			//this will check whether there are any preload errors
			//do something -- return to error page
			//currently returning the same page, but should redirect to error page.
			return CONTACT_DETAILS_FORM;
		}

		contactDetailsValidator.validate(contactDetails, error);

		if(error.hasErrors()){
			generator.setLocation(PropertyFileEnum.CONTACT_DETAILS_PROPERTY.value());
			generator.parseModel(model);
			model.addAttribute(contactDetails);
			logger.debug(error);
			return CONTACT_DETAILS_FORM;
		}
		session.setAttribute(CONTACT_DETAILS, contactDetails);
		session.setAttribute(ControllerUrls.LAST_COMPLETED_FORM, ControllerUrls.CONTACT_DETAILS_URL);

		logger.info(sessionId + "Saved Claimant Details");
		return "redirect:" + NEXT_FORM;

	}
}
