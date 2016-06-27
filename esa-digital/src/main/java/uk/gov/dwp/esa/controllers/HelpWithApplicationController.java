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

import uk.gov.dwp.esa.model.HelpDetails;
import uk.gov.dwp.esa.util.ControllerUrls;
import uk.gov.dwp.esa.validators.HelpDetailsValidator;

@Controller
public class HelpWithApplicationController {

	private static final Logger logger = LogManager.getLogger(HelpWithApplicationController.class);
	protected static final String HELP_DETAILS = "HelpDetails";
	private String NEXT_FORM = ControllerUrls.CONTACT_DETAILS_URL;
	private static String HELP_DETAILS_FORM = "help-details";

	@Autowired
	HelpDetailsValidator helpDetailsValidator;

	@RequestMapping(value = ControllerUrls.HELP_DETAILS_FORM, method = RequestMethod.GET)
	public String getPersonalDetailsForm(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		String sessionId = session.getId();
		

		logger.debug(sessionId + " Getting help with appication form");

		HelpDetails helpDetails = (HelpDetails) session.getAttribute(HELP_DETAILS);
		if (helpDetails == null) {
			helpDetails = new HelpDetails();
		} else {
			model.addAttribute(helpDetails);
		}

		return HELP_DETAILS_FORM;
	}

	@RequestMapping(value = ControllerUrls.HELP_DETAILS_FORM, method = RequestMethod.POST)
	public String saveHelpWithApplicationData(Model model, HelpDetails helpDetails, BindingResult error, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String sessionId = session.getId();

		logger.debug(sessionId + "Saving Help Details");

		if (error.hasErrors()) {

			return HELP_DETAILS_FORM;
		}

		helpDetailsValidator.validate(helpDetails, error);

		if (error.hasErrors()) {
			model.addAttribute(helpDetails);
			logger.debug(error);
			return HELP_DETAILS_FORM;
		}
		session.setAttribute(HELP_DETAILS, helpDetails);
		session.setAttribute(ControllerUrls.LAST_COMPLETED_FORM, ControllerUrls.HELP_DETAILS_URL);

		logger.debug(sessionId + "Saved Help Details");
		return "redirect:" + NEXT_FORM;

	}

}
