package uk.gov.dwp.esa.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import uk.gov.dwp.esa.constants.ClaimantConstants;
import uk.gov.dwp.esa.constants.ContactDetailsConstants;
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.Claimant;
import uk.gov.dwp.esa.model.ContactDetails;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;
import uk.gov.dwp.esa.validatorHelpers.ValidationUtils;

@Component
public class ContactDetailsValidator implements Validator {

	private static final int maxLength = 27;

	@Autowired
	private MessageSource messageSource;

	/*
	 * This method is used to validate the basic claimant properties gathered
	 * from front end.
	 * 
	 * @Name : validateClaimant
	 * 
	 * @ReturnType : List of ValidationError
	 * 
	 * @Params : Claimant
	 */
	protected List<ValidationError> validateContactDetails(ContactDetails contactDetails) {

		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (contactDetails == null) {
			contactDetails = new ContactDetails();
		}
		// check mandatory fields
		// This is a title validation - checks if empty. If not empty, checks
		// length (!>27)
		if (ValidationUtils.isEmpty(contactDetails.getAddressLine1())) {
			ValidationError addressLine1Validation = new ValidationError(ContactDetailsConstants.ADDRESSLINE1.value(),
					messageSource.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS1_EMPTY, null, Locale.ENGLISH));
			errors.add(addressLine1Validation);
		} else {
			if (ValidationUtils.isStringLengthOver(contactDetails.getAddressLine1(), maxLength)) {
				errors.add(new ValidationError(ContactDetailsConstants.ADDRESSLINE1.value(), messageSource
						.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS1_TOO_LONG, null, Locale.ENGLISH)));
			}
			// Checks if title is alpha only
			if (!ValidationUtils.isAlphaOnly(contactDetails.getAddressLine1())) {
				errors.add(new ValidationError(ClaimantConstants.TITLE.value(),
						messageSource.getMessage(ValidationCodes.CLAIMANT_TITLE_ALPHA, null, Locale.ENGLISH)));
			}
		}

		if (ValidationUtils.isEmpty(contactDetails.getAddressLine2())) {
			ValidationError addressLine1Validation = new ValidationError(ContactDetailsConstants.ADDRESSLINE2.value(),
					messageSource.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS2_EMPTY, null, Locale.ENGLISH));
			errors.add(addressLine1Validation);
		} else {
			if (ValidationUtils.isStringLengthOver(contactDetails.getAddressLine2(), maxLength)) {
				errors.add(new ValidationError(ContactDetailsConstants.ADDRESSLINE2.value(), messageSource
						.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS2_TOO_LONG, null, Locale.ENGLISH)));
			}
			// Checks if title is alpha only
			if (!ValidationUtils.isAlphaOnly(contactDetails.getAddressLine2())) {
				errors.add(new ValidationError(ClaimantConstants.TITLE.value(),
						messageSource.getMessage(ValidationCodes.CLAIMANT_TITLE_ALPHA, null, Locale.ENGLISH)));
			}
		}

		return errors;
	}

	@Override
	public boolean supports(Class clazz) {
		return Claimant.class.equals(clazz);
	}

	@Override
	public void validate(Object contactDetails, Errors errors) {

		List<ValidationError> validationErrors = this.validateContactDetails((ContactDetails) contactDetails);

		for (ValidationError error : validationErrors) {
			errors.rejectValue(error.getFieldId(), error.getErrorMessage());
		}

	}

}
