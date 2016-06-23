package uk.gov.dwp.esa.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import uk.gov.dwp.esa.constants.AlternateFormatsConstants;
import uk.gov.dwp.esa.constants.GpDetailsConstants;
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.AlternativeFormat;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;
import uk.gov.dwp.esa.validatorHelpers.ValidationUtils;

@Component
public class AlternativeFormatsValidator implements Validator {

	private static final int maxLength = 25;
	
	@Autowired
    private MessageSource messageSource;
	/*
	 * This method is used to validate the basic claimant properties 
	 * gathered from front end.
	 * 
	 * @Name : validateAlternativeFormats
	 * @ReturnType : List of ValidationError
	 * @Params : AlternativeFormats
	 */
	protected List<ValidationError> validateAlternativeFormats(AlternativeFormat alternativeFormat) {
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		

		
		//check Q1 is not empty
		if(ValidationUtils.isEmpty(alternativeFormat.getAlternativeFormatYN())){
			ValidationError isAlternativeFormatYN = new ValidationError(AlternateFormatsConstants.ALTERNATE_FORMAT.value(), ValidationCodes.ALTERNATE_FORMAT_EMPTY);
			errors.add(isAlternativeFormatYN);
		}	
	
		//Check Q2 is not empty if Q1 = 'Yes'
		
		if (alternativeFormat.getAlternativeFormatYN().equals("Yes")) {
			if(ValidationUtils.isEmpty(alternativeFormat.getAlternateFormatType())){
			ValidationError alternativeFormatTypeValidation = new ValidationError(AlternateFormatsConstants.ALTERNATE_FORMAT_TYPE.value(), ValidationCodes.ALTERNATE_FORMAT_TYPE_EMPTY);
			errors.add(alternativeFormatTypeValidation);
		}
		}
		
	//	Check Q3 is not empty if Q2 = 'Other'
		
		if (alternativeFormat.getAlternateFormatType().equals("Other")) {
			if(ValidationUtils.isEmpty(alternativeFormat.getAlternateFormatOther())){
			ValidationError alternativeFormatOtherValidation = new ValidationError(AlternateFormatsConstants.ALTERNATE_FORMAT_OTHER.value(), ValidationCodes.ALTERNATE_FORMAT_OTHER_EMPTY);
			errors.add(alternativeFormatOtherValidation);
		} if (ValidationUtils.isStringLengthOver(alternativeFormat.getAlternateFormatOther(), maxLength)) {
				errors.add(new ValidationError(AlternateFormatsConstants.ALTERNATE_FORMAT_OTHER.value(),
				ValidationCodes.ALTERNATE_FORMAT_OTHER_TOO_LONG));
			}
	}
	
	return errors;
	}

	@Override
	public boolean supports(Class clazz) {
		return AlternativeFormat.class.equals(clazz);
	}

	@Override
	public void validate(Object alternativeFormats, Errors errors) {

		List<ValidationError> validationErrors = this.validateAlternativeFormats((AlternativeFormat) alternativeFormats);

		for (ValidationError error : validationErrors) {
			errors.rejectValue(error.getFieldId(), error.getErrorMessage());
		}

	}
}
