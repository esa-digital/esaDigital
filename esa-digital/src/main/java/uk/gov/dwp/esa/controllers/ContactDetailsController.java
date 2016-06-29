package uk.gov.dwp.esa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.gov.dwp.esa.constants.PropertyFileEnum;
import uk.gov.dwp.esa.model.ContactDetails;
import uk.gov.dwp.esa.util.ControllerUrls;
import uk.gov.dwp.esa.validators.ContactDetailsValidator;

@Controller
@RequestMapping(ControllerUrls.CONTACT_DETAILS_FORM)
public class ContactDetailsController extends GenericController<ContactDetails>{

	protected static final String CONTACT_DETAILS = "ContactDetails";
	private static String NEXT_FORM = ControllerUrls.ALTERNATIVE_FORMATS_URL;

	@Autowired
	private ContactDetailsValidator contactDetailsValidator;
	
	public ContactDetailsController() {
		super(PropertyFileEnum.CONTACT_DETAILS_PROPERTY.value(),CONTACT_DETAILS,ControllerUrls.CONTACT_DETAILS_FORM ,NEXT_FORM);
		ContactDetails contact = new ContactDetails();
		super.setObject(contact);
	}

	@Override
	public void validate(ContactDetails object, BindingResult error) {
		contactDetailsValidator.validate(object, error);
		
	}

}
