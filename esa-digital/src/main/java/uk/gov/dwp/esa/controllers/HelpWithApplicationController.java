package uk.gov.dwp.esa.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

import uk.gov.dwp.esa.util.ControllerUrls;

@Controller
public class HelpWithApplicationController {
	
	private static final Logger logger = LogManager.getLogger(HelpWithApplicationController.class);
	protected static final String HELP_DETAILS = "HelpDetails";
	private String NEXT_FORM = "/api" + ControllerUrls.PERSONAL_DETAILS_FORM;
	private static String HELP_DETAILS_FORM = "help-details";

}
