package uk.gov.dwp.esa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.gov.dwp.esa.constants.PropertyFileEnum;
import uk.gov.dwp.esa.model.Claimant;
import uk.gov.dwp.esa.util.ControllerUrls;
import uk.gov.dwp.esa.validators.ClaimantValidator;

@Controller
@RequestMapping(ControllerUrls.PERSONAL_DETAILS_FORM)
public class ClaimantController extends GenericController<Claimant> {

	protected static final String CLAIMANT_DETAILS = "Claimant";
	private static String NEXT_FORM = ControllerUrls.CONTACT_DETAILS_URL;
	private static String PREVIOUS_FORM = ControllerUrls.HELP_DETAILS_URL;
	
	@Autowired
	private ClaimantValidator claimantValidator;

	public ClaimantController() {

		super(PropertyFileEnum.CLAIMANT_PROPERTY.value(), CLAIMANT_DETAILS, ControllerUrls.PERSONAL_DETAILS_FORM,
				NEXT_FORM,PREVIOUS_FORM);
		super.setValidator(claimantValidator);
		Claimant claimant = new Claimant();
		super.setObject(claimant);
	}

	@Override
	public void validate(Claimant object, BindingResult error) {
		claimantValidator.validate(object, error);

	}

}
