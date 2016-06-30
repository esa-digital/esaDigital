package uk.gov.dwp.esa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.gov.dwp.esa.constants.PropertyFileEnum;
import uk.gov.dwp.esa.model.AlternativeFormat;
import uk.gov.dwp.esa.service.ESAClaimService;
import uk.gov.dwp.esa.util.ControllerUrls;
import uk.gov.dwp.esa.validators.AlternativeFormatsValidator;

@Controller
@RequestMapping(ControllerUrls.ALTERNATIVE_FORMATS_FORM)
public class AlternativeFormatsController extends GenericController<AlternativeFormat>{

	

	private static String ALT_FORMATS = "alternative-formats";
	private static String NEXT_FORM = ControllerUrls.HOME_URL;
	private static String PREVIOUS_FORM = ControllerUrls.CONTACT_DETAILS_URL;

	@Autowired
	private ESAClaimService esaClaimService;

	@Autowired
	private AlternativeFormatsValidator alternativeFormatsValidator;
	
	public AlternativeFormatsController() {
		super(PropertyFileEnum.ALTERNATIVE_FORMATS_PROPERTY.value(),ALT_FORMATS,ControllerUrls.ALTERNATIVE_FORMATS_FORM ,NEXT_FORM,PREVIOUS_FORM);
		AlternativeFormat alternativeFormat = new AlternativeFormat();
		super.setObject(alternativeFormat);
	}

	
	/*public void postFormData(HttpSession session) {

		
		Claim claim = populateClaimObject(session);

		ResponseEntity<Map> status = esaClaimService.submitClaimDetails(claim);
		HttpStatus httpStatus = status.getStatusCode();
		if(httpStatus.equals(HttpStatus.CREATED)){
		}

	}

	private Claim populateClaimObject(HttpSession session){
		
		String tokenList = (String) session.getAttribute(TokenServiceController.TOKEN_SESSION_ATTRIBUTE);
		String[] tokens = tokenList.split(":");

		HelpDetails helpDetails = (HelpDetails) session.getAttribute(HelpWithApplicationController.HELP_DETAILS);
		Claimant claimant = (Claimant) session.getAttribute(ClaimantController.CLAIMANT_DETAILS);
		ContactDetails contactDetails = (ContactDetails) session.getAttribute(ContactDetailsController.CONTACT_DETAILS);
		AlternativeFormat alternativeFormat = (AlternativeFormat) session.getAttribute(AlternativeFormatsController.ALT_FORMATS);

		Claim claim = new Claim();
		claim.setThirdParty(helpDetails);
		claim.setClaimant(claimant);
		claim.setContactDetails(contactDetails);
		claim.setAlternativeFormat(alternativeFormat);
		claim.setToken(tokens[1]);
		return claim;

	}
*/
	@Override
	public void validate(AlternativeFormat object, BindingResult error) {
		alternativeFormatsValidator.validate(object, error);
	}

}
