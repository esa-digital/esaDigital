package uk.gov.dwp.esa.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import uk.gov.dwp.esa.constants.ContactDetailsConstants;
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.ContactDetails;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;
import uk.gov.dwp.esa.validatorHelpers.ValidationUtils;

@Component
public class ContactDetailsValidator implements Validator {

	private static final int maxLengthAddressLine = 27;

	private static final int maxLengthPostcode = 8;

	private static final int maxLenghtPhone = 20;

	private static final int maxLenghtEmail = 50;



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
		// This is an address validation - checks if empty. If not empty, checks
		// length (!>27)
		if (ValidationUtils.isEmpty(contactDetails.getAddressLine1())) {
			ValidationError addressLine1Validation = new ValidationError(ContactDetailsConstants.ADDRESSLINE1.value(),
					ValidationCodes.CONTACT_DETAILS_ADDRESS1_EMPTY);
			errors.add(addressLine1Validation);
		} else {
			if (ValidationUtils.isStringLengthOver(contactDetails.getAddressLine1(), maxLengthAddressLine)) {
				errors.add(new ValidationError(ContactDetailsConstants.ADDRESSLINE1.value(), ValidationCodes.CONTACT_DETAILS_ADDRESS1_TOO_LONG));
			}
			// Checks if address line 1 uses valid characters
			if (!ValidationUtils.isValidAddress(contactDetails.getAddressLine1())) {
				errors.add(new ValidationError(ContactDetailsConstants.ADDRESSLINE1.value(),ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE1_INVALIDCHARS));
			}
		}

		if (ValidationUtils.isEmpty(contactDetails.getAddressLine2())) {
			ValidationError addressLine1Validation = new ValidationError(ContactDetailsConstants.ADDRESSLINE2.value(),
					ValidationCodes.CONTACT_DETAILS_ADDRESS2_EMPTY);
			errors.add(addressLine1Validation);
		} else {
			if (ValidationUtils.isStringLengthOver(contactDetails.getAddressLine2(), maxLengthAddressLine)) {
				errors.add(new ValidationError(ContactDetailsConstants.ADDRESSLINE2.value(), 
						ValidationCodes.CONTACT_DETAILS_ADDRESS2_TOO_LONG));
			}
			// Checks if address line 2 uses valid characters
			if (!ValidationUtils.isValidAddress(contactDetails.getAddressLine2())) {
				errors.add(new ValidationError(ContactDetailsConstants.ADDRESSLINE2.value(),
						ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE2_INVALIDCHARS));
			}
		}

		if (!ValidationUtils.isEmpty(contactDetails.getAddressLine3())) {

			if (ValidationUtils.isStringLengthOver(contactDetails.getAddressLine3(), maxLengthAddressLine)) {
				errors.add(new ValidationError(ContactDetailsConstants.ADDRESSLINE3.value(), 
						ValidationCodes.CONTACT_DETAILS_ADDRESS3_TOO_LONG));
			}
			// Checks if address line 3 uses valid characters.
			if (!ValidationUtils.isValidAddress(contactDetails.getAddressLine3())) {
				errors.add(new ValidationError(ContactDetailsConstants.ADDRESSLINE3.value(), 
						ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE3_INVALIDCHARS));
			}
		}

		if (!ValidationUtils.isEmpty(contactDetails.getAddressLine4())) {

			if (ValidationUtils.isStringLengthOver(contactDetails.getAddressLine4(), maxLengthAddressLine)) {
				errors.add(new ValidationError(ContactDetailsConstants.ADDRESSLINE4.value(), 
						ValidationCodes.CONTACT_DETAILS_ADDRESS4_TOO_LONG));
			}
			// Checks if address line 4 uses valid characters.
			if (!ValidationUtils.isValidAddress(contactDetails.getAddressLine4())) {
				errors.add(new ValidationError(ContactDetailsConstants.ADDRESSLINE4.value(), 
						ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE4_INVALIDCHARS));
			}
		}

		if (ValidationUtils.isEmpty(contactDetails.getPostCode())) {
			ValidationError postcodeValidation = new ValidationError(ContactDetailsConstants.POSTCODE.value(),
					ValidationCodes.CONTACT_DETAILS_POSTCODE_EMPTY);
			errors.add(postcodeValidation);
		} else {
			if (ValidationUtils.isStringLengthOver(contactDetails.getPostCode(), maxLengthPostcode)) {
				errors.add(new ValidationError(ContactDetailsConstants.POSTCODE.value(), 
						ValidationCodes.CONTACT_DETAILS_POSTCODE_TOO_LONG));
			}
			// Checks if postcode uses valid characters only
			if (!ValidationUtils.isValidPostcode(contactDetails.getPostCode())) {
				errors.add(new ValidationError(ContactDetailsConstants.POSTCODE.value(),
						ValidationCodes.COMPANY_POSTCODE_INVALID));
			}
		}

		if (!ValidationUtils.isEmpty(contactDetails.getPhoneNumber())) {

			if (ValidationUtils.isStringLengthOver(contactDetails.getPhoneNumber(), maxLenghtPhone)) {
				errors.add(new ValidationError(ContactDetailsConstants.PHONENUMBER.value(), ValidationCodes.CONTACT_DETAILS_PHONENUMBER_TOO_LONG));
			}
			// Checks if telephone uses valid characters only
			if (!ValidationUtils.isTelephoneOnly(contactDetails.getPhoneNumber())) {
				errors.add(new ValidationError(ContactDetailsConstants.PHONENUMBER.value(),
						ValidationCodes.CONTACT_DETAILS_PHONENUMBER_INVALID));
			}
		}

		if (!ValidationUtils.isEmpty(contactDetails.getOtherNumber())) {

			if (ValidationUtils.isStringLengthOver(contactDetails.getOtherNumber(), maxLenghtPhone)) {
				errors.add(new ValidationError(ContactDetailsConstants.OTHERNUMBER.value(), ValidationCodes.CONTACT_DETAILS_OTHERNUMBER_TOO_LONG));
			}
			// Checks if contact number uses valid characters only
			if (!ValidationUtils.isTelephoneOnly(contactDetails.getOtherNumber())) {
				errors.add(new ValidationError(ContactDetailsConstants.OTHERNUMBER.value(),
						ValidationCodes.CONTACT_DETAILS_OTHERNUMBER_INVALID));
			}
		}

		if (!ValidationUtils.isEmpty(contactDetails.getEmail())) {

			if (ValidationUtils.isStringLengthOver(contactDetails.getEmail(), maxLenghtEmail)) {
				errors.add(new ValidationError(ContactDetailsConstants.EMAIL.value(),
						ValidationCodes.CONTACT_EMAIL_TOO_LONG));
			}
			// Checks if email address uses valid characters
			if (!ValidationUtils.isEmailValid(contactDetails.getEmail())) {
				errors.add(new ValidationError(ContactDetailsConstants.EMAIL.value(),
						ValidationCodes.CONTACT_EMAIL_INVALID));
			}
		}

		return errors;
	}

	@Override
	public boolean supports(Class clazz) {
		return ContactDetails.class.equals(clazz);
	}

	@Override
	public void validate(Object contactDetails, Errors errors) {

		List<ValidationError> validationErrors = this.validateContactDetails((ContactDetails) contactDetails);

		for (ValidationError error : validationErrors) {
			errors.rejectValue(error.getFieldId(), error.getErrorMessage());
		}

	}

}
