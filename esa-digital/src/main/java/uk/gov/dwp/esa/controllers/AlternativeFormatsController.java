package uk.gov.dwp.esa.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.gov.dwp.esa.model.AlternativeFormat;
import uk.gov.dwp.esa.model.Claim;
import uk.gov.dwp.esa.model.Claimant;
import uk.gov.dwp.esa.model.GPDetails;
import uk.gov.dwp.esa.service.ESAClaimService;
import uk.gov.dwp.esa.util.ControllerUrls;
import uk.gov.dwp.esa.validators.AlternativeFormatsValidator;

@Controller
public class AlternativeFormatsController {
	
	private static final Logger logger = LogManager.getLogger(GPController.class);
	private static final String ALT_FORMATS = "/alternative-formats";
	private static String ALT_FORMATS_FORM = "alternative-formats";

	@Autowired
	private ESAClaimService esaClaimService;

	@Autowired
	private AlternativeFormatsValidator alternativeFormatsValidator;

	@RequestMapping(value = ControllerUrls.ALTERNATIVE_FORMATS, method = RequestMethod.GET)
	public String getAlternativeFormatsForm(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String sessionId = session.getId();

		logger.debug(sessionId + " getting alternative formats form");
		
		AlternativeFormat alternativeFormat = (AlternativeFormat) session.getAttribute(ALT_FORMATS);

		if (alternativeFormat == null) {
			alternativeFormat = new AlternativeFormat();
		} else {
			model.addAttribute(ALT_FORMATS, alternativeFormat);
		}
		return ALT_FORMATS_FORM;

	}

	@RequestMapping(value = ControllerUrls.ALTERNATIVE_FORMATS, method = RequestMethod.POST)
	public String saveAlternativeFormatsData(Model model, AlternativeFormat alternativeFormat, BindingResult error, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String sessionId = session.getId();

		logger.debug(sessionId + " Getting GP details form");

		if (error.hasErrors()) {
			// this will check whether there are any preload errors
			// do something -- return to error page
			// currently returning the same page, but should redirect to error
			// page.
			return ALT_FORMATS_FORM;
		}

		alternativeFormatsValidator.validate(alternativeFormat, error);

		if (error.hasErrors()) {
			model.addAttribute(ALT_FORMATS, alternativeFormat);
			logger.debug(error);
			return ALT_FORMATS_FORM;
		}

		session.setAttribute(ALT_FORMATS, alternativeFormat);

		Claim claim = populateClaimObject(request);

		ResponseEntity<Map> status = esaClaimService.submitClaimDetails(claim);
		HttpStatus httpStatus = status.getStatusCode();
		if(httpStatus.equals(HttpStatus.CREATED)){
			return "home";
		}

		return null;
	}

		private Claim populateClaimObject(HttpServletRequest request){

		HttpSession session = request.getSession();
		String tokenList = (String) session.getAttribute(TokenServiceController.TOKEN_SESSION_ATTRIBUTE);
		String[] tokens = tokenList.split(":");
		
		Claimant claimant = (Claimant) session.getAttribute(ClaimantController.CLAIMANT_DETAILS);
		GPDetails gpDetails = (GPDetails) session.getAttribute(GPController.GP_DETAILS);
		AlternativeFormat alternativeFormat = (AlternativeFormat) session.getAttribute(AlternativeFormatsController.ALT_FORMATS);
		
		Claim claim = new Claim();
		claim.setClaimant(claimant);
		claim.setGpDetails(gpDetails);
		claim.setAlternativeFormat(alternativeFormat);
		claim.setToken(tokens[1]);
		return claim;

	}

}
