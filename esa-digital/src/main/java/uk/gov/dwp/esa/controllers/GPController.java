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

import uk.gov.dwp.esa.model.Claim;
import uk.gov.dwp.esa.model.Claimant;
import uk.gov.dwp.esa.model.GPDetails;
import uk.gov.dwp.esa.service.ESAClaimService;
import uk.gov.dwp.esa.util.ControllerUrls;
import uk.gov.dwp.esa.validators.GpDetailsValidator;

@Controller
public class GPController {

	private static final Logger logger = LogManager.getLogger(GPController.class);
	protected static final String GP_DETAILS = "GPDetails";
	private static String GP_DETAILS_FORM = "gp-details";

	@Autowired
	private ESAClaimService esaClaimService;

	@Autowired
	private GpDetailsValidator gpValidator;

	@RequestMapping(value = ControllerUrls.GP_DETAILS_FORM, method = RequestMethod.GET)
	public String getGPDetailsForm(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String sessionId = session.getId();

		logger.debug(sessionId + " Getting GP details form");

		GPDetails gpDetails = (GPDetails) session.getAttribute(GP_DETAILS);

		if (gpDetails == null) {
			gpDetails = new GPDetails();
		}
		
		model.addAttribute(gpDetails);
		
		return GP_DETAILS_FORM;

	}

	@RequestMapping(value = ControllerUrls.GP_DETAILS_FORM, method = RequestMethod.POST)
	public String saveGPDetailsData(Model model, GPDetails gpDetails, BindingResult error, HttpServletRequest request) {

		HttpSession session = request.getSession();
		String sessionId = session.getId();

		logger.debug(sessionId + " Getting GP details form");

		if (error.hasErrors()) {
			// this will check whether there are any preload errors
			// do something -- return to error page
			// currently returning the same page, but should redirect to error
			// page.
			return GP_DETAILS_FORM;
		}

		gpValidator.validate(gpDetails, error);

		if (error.hasErrors()) {
			model.addAttribute(gpDetails);
			logger.debug(error);
			return GP_DETAILS_FORM;
		}

		session.setAttribute(GP_DETAILS, gpDetails);

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
		GPDetails gpDetails = (GPDetails) session.getAttribute(GP_DETAILS);

		Claim claim = new Claim();
		claim.setClaimant(claimant);
		claim.setGpDetails(gpDetails);
		claim.setToken(tokens[1]);
		return claim;

	}
}
