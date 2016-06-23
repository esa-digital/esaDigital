package uk.gov.dwp.esa.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import uk.gov.dwp.esa.constants.GpDetailsConstants;
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.GPDetails;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;
import uk.gov.dwp.esa.validatorHelpers.ValidationUtils;

@Component
public class GpDetailsValidator implements Validator {

	private static final int maxLength = 27;
	private static final int maxLengthPostCode = 8;
	private static final int maxLengthPhone = 20;
	

	/*
	 * This method is used to validate the basic claimant properties 
	 * gathered from front end.
	 * 
	 * @Name : validateGPDetails
	 * @ReturnType : List of ValidationError
	 * @Params : GPDetails
	 */
	protected List<ValidationError> validateGPDetails(GPDetails gpDetails){
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if(gpDetails==null){
			gpDetails = new GPDetails();
		}
		
		//check mandatory fields
		//This is a name validation - checks if empty. If not empty, checks length (!>27)
		if(ValidationUtils.isEmpty(gpDetails.getDoctorName())){
			ValidationError doctorNameValidation = new ValidationError(GpDetailsConstants.DOCTOR_NAME.value(),ValidationCodes.DOCTOR_NAME_EMPTY);
			errors.add(doctorNameValidation);
		}
		else
		{
			if(ValidationUtils.isStringLengthOver(gpDetails.getDoctorName(), maxLength)){
				errors.add(new ValidationError(GpDetailsConstants.DOCTOR_NAME.value(),ValidationCodes.DOCTOR_NAME_TOO_LONG));
			}
			// Checks if name is alpha only
			if(!ValidationUtils.isValidName(gpDetails.getDoctorName())){
				errors.add(new ValidationError(GpDetailsConstants.DOCTOR_NAME.value(),ValidationCodes.DOCTOR_NAME_INVALIDCHARS));
			}
		}
	
		//address line1 check	
		
		if(ValidationUtils.isEmpty(gpDetails.getDocAddLine1())){
			ValidationError doctorAddressLine1Validation = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE1.value(), ValidationCodes.DOCTOR_ADDRESS_LINE1_EMPTY);
			errors.add(doctorAddressLine1Validation );
		}else{
			if(ValidationUtils.isStringLengthOver(gpDetails.getDocAddLine1(), maxLength )){
				errors.add(new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE1.value(),ValidationCodes.DOCTOR_ADDRESS_LINE1_TOO_LONG));
			}
						
			if(!ValidationUtils.isValidAddress(gpDetails.getDocAddLine1())){
				errors.add(new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE1.value(),ValidationCodes.DOCTOR_ADDRESS_LINE1_INVALIDCHARS));
			}
		}
			

		if(ValidationUtils.isEmpty(gpDetails.getDocAddLine2())){
			ValidationError doctorAddressLine2Validation = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE2.value(),ValidationCodes.DOCTOR_ADDRESS_LINE2_EMPTY);
			errors.add(doctorAddressLine2Validation );
		}else{
			if(ValidationUtils.isStringLengthOver(gpDetails.getDocAddLine2(), maxLength )){
				errors.add(new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE2.value(), ValidationCodes.DOCTOR_ADDRESS_LINE2_TOO_LONG));
			}
						
			if(!ValidationUtils.isValidAddress(gpDetails.getDocAddLine2())){
				errors.add(new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE2.value(),ValidationCodes.DOCTOR_ADDRESS_LINE2_INVALIDCHARS));
			}
		}
			

		if (gpDetails.getDocAddLine3() != null && !gpDetails.getDocAddLine3().equals("")) {
			if (ValidationUtils.isStringLengthOver(gpDetails.getDocAddLine3(), maxLength)) {
				errors.add(new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE3.value(),
						ValidationCodes.DOCTOR_ADDRESS_LINE3_TOO_LONG));
			}

			if (!ValidationUtils.isValidAddress(gpDetails.getDocAddLine3())) {
				errors.add(new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE3.value(),
						ValidationCodes.DOCTOR_ADDRESS_LINE3_INVALIDCHARS));
			}
		}
			
		if (gpDetails.getDocAddLine4() != null && !gpDetails.getDocAddLine4().equals("")) {
			if (ValidationUtils.isStringLengthOver(gpDetails.getDocAddLine4(), maxLength)) {
				errors.add(new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE4.value(),
						ValidationCodes.DOCTOR_ADDRESS_LINE4_TOO_LONG));
			}

			if (!ValidationUtils.isValidAddress(gpDetails.getDocAddLine4())) {
				errors.add(new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE4.value(), ValidationCodes.DOCTOR_ADDRESS_LINE4_INVALIDCHARS));
			}
		}
						
		if (ValidationUtils.isEmpty(gpDetails.getDocPostCode())) {
			ValidationError docPostCodeValidation = new ValidationError(GpDetailsConstants.DOCTOR_POSTCODE.value(),
					ValidationCodes.DOCTOR_POSTCODE_EMPTY);
			errors.add(docPostCodeValidation);
		} else {
			//isValidPostcode covers both postcode validity and length. I.e., if the postcode is too long - it will show as invalid, hence a separate error message for this is not required.
			if (!ValidationUtils.isValidPostcode(gpDetails.getDocPostCode())) {
				errors.add(new ValidationError(GpDetailsConstants.DOCTOR_POSTCODE.value(),
						ValidationCodes.DOCTOR_POSTCODE_INVALID));
			}
		}
				
			if(ValidationUtils.isEmpty(gpDetails.getDocTelNumber())){
				ValidationError docTelNumberValidation = new ValidationError(GpDetailsConstants.DOCTOR_TEL_NUMBER.value(), ValidationCodes.DOCTOR_TELEPHONE_EMPTY);
				errors.add(docTelNumberValidation);
			}else{
				if(ValidationUtils.isStringLengthOver(gpDetails.getDocTelNumber(), maxLengthPhone)){
					errors.add(new ValidationError(GpDetailsConstants.DOCTOR_TEL_NUMBER.value(), ValidationCodes.DOCTOR_TELEPHONE_TOO_LONG));
				}	
				
				if(!ValidationUtils.isTelephoneOnly(gpDetails.getDocTelNumber())){
					errors.add(new ValidationError(GpDetailsConstants.DOCTOR_TEL_NUMBER.value(), ValidationCodes.DOCTOR_TELEPHONE_NUMERIC));
				}
			}
				
		return errors;
	}

	@Override
	public boolean supports(Class clazz) {
		return GPDetails.class.equals(clazz);
	}

	@Override
	public void validate(Object gpDetails, Errors errors) {

		List<ValidationError> validationErrors = this.validateGPDetails((GPDetails) gpDetails);

		for (ValidationError error : validationErrors) {
			errors.rejectValue(error.getFieldId(), error.getErrorMessage());
		}

	}
}
