package uk.gov.dwp.esa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.gov.dwp.esa.constants.PropertyFileEnum;
import uk.gov.dwp.esa.model.HelpDetails;
import uk.gov.dwp.esa.model.HelpDetailsType;
import uk.gov.dwp.esa.util.ControllerUrls;
import uk.gov.dwp.esa.validators.HelpDetailsValidator;

@Controller
@RequestMapping(ControllerUrls.HELP_DETAILS_FORM)
public class HelpWithApplicationController extends GenericController<HelpDetails> {

	protected static final String HELP_DETAILS = "HelpDetails";
	private static String NEXT_FORM = ControllerUrls.PERSONAL_DETAILS_URL;
	private static String PREVIOUS_FORM = ControllerUrls.START_YOUR_APPLICATION_URL;

	@Autowired
	HelpDetailsValidator helpDetailsValidator;

	public HelpWithApplicationController() {
		super(PropertyFileEnum.HELP_DETAILS_PROPERTY.value(), HELP_DETAILS, ControllerUrls.HELP_DETAILS_FORM, NEXT_FORM,
				PREVIOUS_FORM);
		HelpDetails helpDetails = new HelpDetails();
		HelpDetailsType type = new HelpDetailsType();
		helpDetails.setThirdPartyDetails(type);
		super.setObject(helpDetails);
	}

	@Override
	public void validate(HelpDetails object, BindingResult error) {
		helpDetailsValidator.validate(object, error);

	}

}
