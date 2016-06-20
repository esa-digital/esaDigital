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
import uk.gov.dwp.esa.validators.HelpDetailsValidator;

@Controller
public class HelpWithApplicationController {

	private static final Logger logger = LogManager.getLogger(HelpWithApplicationController.class);
	protected static final String HELP_DETAILS = "HelpDetails";
	private String NEXT_FORM = "/api" + ControllerUrls.PERSONAL_DETAILS_FORM;
	private static String HELP_DETAILS_FORM = "help-details";

	@Autowired
	HelpDetailsValidator helpDetailsValidator;

	@RequestMapping(value = ControllerUrls.HELP_DETAILS_FORM, method = RequestMethod.GET)
	public String getPersonalDetailsForm(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String sessionId = session.getId();

		logger.debug(sessionId + " Getting personal details form");

		Claimant claimant = (Claimant) session.getAttribute(HELP_DETAILS);
		if (claimant == null) {
			claimant = new Claimant();
		} else {
			model.addAttribute(HELP_DETAILS, claimant);
		}

		return HELP_DETAILS_FORM;
	}

	@RequestMapping(value = ControllerUrls.HELP_DETAILS_FORM, method = RequestMethod.POST)
	public String saveClaimantData(Model model, Claimant claimant, BindingResult error, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String sessionId = session.getId();

		logger.debug(sessionId + "Saving Claimant Details");

		if (error.hasErrors()) {

			return HELP_DETAILS_FORM;
		}

		helpDetailsValidator.validate(claimant, error);

		if (error.hasErrors()) {
			model.addAttribute(HELP_DETAILS, claimant);
			logger.debug(error);
			return HELP_DETAILS_FORM;
		}
		session.setAttribute(HELP_DETAILS, claimant);

		logger.debug(sessionId + "Saved Claimant Details");
		return "redirect:" + NEXT_FORM;

	}

}
