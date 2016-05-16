package uk.gov.dwp.esa.validators;

import java.util.ArrayList;
import java.util.List;

import uk.gov.dwp.esa.constants.ClaimantConstants;
import uk.gov.dwp.esa.model.Claimant;
import uk.gov.dwp.esa.validatorHelpers.ValidationCodes;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;
import uk.gov.dwp.esa.validatorHelpers.ValidationUtils;

public class ClaimantValidator {

	private static final int maxLength = 27;
	/*
	 * This method is used to validate the basic claimant properties 
	 * gathered from front end.
	 * 
	 * @Name : validateClaimant
	 * @ReturnType : List of ValidationError
	 * @Params : Claimant
	 */
	public List<ValidationError> validateClaimant(Claimant claimant){
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if(claimant==null){
			claimant = new Claimant();
		}
		//check mandatory fields
		//This is a title validation - checks if empty. If not empty, checks length (!>27)
		if(ValidationUtils.isEmpty(claimant.getTitle())){
			ValidationError titleValidation = new ValidationError(ClaimantConstants.TITLE.value(), ValidationCodes.CLAIMANT_TITLE_EMPTY);
			errors.add(titleValidation );
		}else{
			if(ValidationUtils.isStringLengthOver(claimant.getTitle(), maxLength )){
				errors.add(new ValidationError(ClaimantConstants.TITLE.value(), ValidationCodes.CLAIMANT_TITLE_TOO_LONG));
			}
			// Checks if title is alpha only
			if(!ValidationUtils.isAlphaOnly(claimant.getTitle())){
				errors.add(new ValidationError(ClaimantConstants.TITLE.value(), ValidationCodes.CLAIMANT_TITLE_ALPHA));
			}
		}
			
		
		if(ValidationUtils.isEmpty(claimant.getFirstName())){
			ValidationError firstNameValidation = new ValidationError(ClaimantConstants.FIRST_NAME.value(), ValidationCodes.CLAIMANT_FIRSTNAME_EMPTY);
			errors.add(firstNameValidation );
		}else{
			if(ValidationUtils.isStringLengthOver(claimant.getFirstName(), maxLength )){
				errors.add(new ValidationError(ClaimantConstants.FIRST_NAME.value(), ValidationCodes.CLAIMANT_FIRST_NAME_TOO_LONG));
			}
						
			if(!ValidationUtils.isAlphaOnly(claimant.getFirstName())){
				errors.add(new ValidationError(ClaimantConstants.FIRST_NAME.value(), ValidationCodes.CLAIMANT_FIRSTNAME_ALPHA));
			}
			
			
		}
		
		if(ValidationUtils.isEmpty(claimant.getSurname())){
			ValidationError surnameValidation = new ValidationError(ClaimantConstants.SURNAME.value(), ValidationCodes.CLAIMANT_SURNAME_EMPTY);
			errors.add(surnameValidation);
		}else{
			if(ValidationUtils.isStringLengthOver(claimant.getSurname(), maxLength )){
				errors.add(new ValidationError(ClaimantConstants.SURNAME.value(), ValidationCodes.CLAIMANT_SURNAME_TOO_LONG));
			}
			
			if(!ValidationUtils.isAlphaOnly(claimant.getSurname())){
				errors.add(new ValidationError(ClaimantConstants.SURNAME.value(), ValidationCodes.CLAIMANT_SURNAME_ALPHA));
			}
			
		}
			
		// Checks if othername is not empty and stringlength is >27 chars.If so, returns error
		if(claimant.getOtherName()!=null && !claimant.getOtherName().equals("") ){
			if(ValidationUtils.isStringLengthOver(claimant.getOtherName(), maxLength)){
				errors.add(new ValidationError(ClaimantConstants.OTHERNAME.value(), ValidationCodes.CLAIMANT_MIDDLE_NAME_TOO_LONG));
			}

			if(!ValidationUtils.isAlphaOnly(claimant.getOtherName())){
				errors.add(new ValidationError(ClaimantConstants.OTHERNAME.value(), ValidationCodes.CLAIMANT_OTHERNAME_ALPHA));
			}
		}

		if(!ValidationUtils.isDateValid(claimant.getDobDay(), claimant.getDobMonth(), claimant.getDobYear())){
			errors.add(new ValidationError(ClaimantConstants.DOB.value() , ValidationCodes.CLAIMANT_DOB_INVALID));
		}else if(ValidationUtils.isDateInTheFuture(claimant.getDobDay(), claimant.getDobMonth(), claimant.getDobYear())){
			errors.add(new ValidationError(ClaimantConstants.DOB.value() , ValidationCodes.CLAIMANT_DOB_FUTURE));
		}else if(!ValidationUtils.isDateSixteenYearsAgo(claimant.getDobDay(), claimant.getDobMonth(), claimant.getDobYear())){
			errors.add(new ValidationError(ClaimantConstants.DOB.value() , ValidationCodes.CLAIMANT_DOB_UNDER_16));
		}
		
		if(ValidationUtils.isEmpty(claimant.getNino())){
			ValidationError ninoValidation = new ValidationError(ClaimantConstants.NINO.value(), ValidationCodes.CLAIMANT_NINO_EMPTY);
			errors.add(ninoValidation);
		}else if(!ValidationUtils.isValidNino(claimant.getNino())){
			ValidationError ninoValidation = new ValidationError(ClaimantConstants.NINO.value(), ValidationCodes.CLAIMANT_NINO_NOTVALID);
			errors.add(ninoValidation);
		}
		
		return errors;
	}
	
}
