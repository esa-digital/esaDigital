package uk.gov.dwp.esa.validators;

import java.util.ArrayList;
import java.util.List;


import uk.gov.dwp.esa.model.Claimant;
import uk.gov.dwp.esa.validatorHelpers.ValidationCodes;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;
import uk.gov.dwp.esa.validatorHelpers.ValidationUtils;

public class ClaimantValidator {

	public static final String TITLE = "claimant.title";
	private static final int maxLength = 27;
	private static final String FIRST_NAME="claimant.firstName";
	private static final String SURNAME="claimant.surname";
	private static final String OTHERNAME="claimant.otherName";
	private  static final String DOB = "claimant.DOB";
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
		if(ValidationUtils.isEmpty(claimant.getTitle())){
			ValidationError titleValidation = new ValidationError(TITLE, ValidationCodes.CLAIMANT_TITLE_EMPTY);
			errors.add(titleValidation );
		}else if(ValidationUtils.isStringLengthOver(claimant.getTitle(), maxLength )){
			errors.add(new ValidationError(TITLE, ValidationCodes.CLAIMANT_TITLE_TOO_LONG));
		}
		
		if(ValidationUtils.isEmpty(claimant.getFirstName())){
			ValidationError firstNameValidation = new ValidationError(FIRST_NAME, ValidationCodes.CLAIMANT_FIRSTNAME_EMPTY);
			errors.add(firstNameValidation );
		}else if(ValidationUtils.isStringLengthOver(claimant.getFirstName(), maxLength )){
			errors.add(new ValidationError(FIRST_NAME, ValidationCodes.CLAIMANT_FIRST_NAME_TOO_LONG));
		}
		
		if(ValidationUtils.isEmpty(claimant.getSurname())){
			ValidationError surnameValidation = new ValidationError(SURNAME, ValidationCodes.CLAIMANT_SURNAME_EMPTY);
			errors.add(surnameValidation);
		}else if(ValidationUtils.isStringLengthOver(claimant.getSurname(), maxLength )){
			errors.add(new ValidationError(SURNAME, ValidationCodes.CLAIMANT_SURNAME_TOO_LONG));
		}
		
		if(!claimant.getOtherName().isEmpty() && ValidationUtils.isStringLengthOver(OTHERNAME, maxLength)){
			errors.add(new ValidationError(OTHERNAME, ValidationCodes.CLAIMANT_MIDDILE_NAME_TOO_LONG));
		}
		
		if(!ValidationUtils.isAlphaOnly(claimant.getFirstName())){
			errors.add(new ValidationError(FIRST_NAME, ValidationCodes.CLAIMANT_FIRSTNAME_ALPHA));
		}
		if(!ValidationUtils.isAlphaOnly(claimant.getSurname())){
			errors.add(new ValidationError(SURNAME, ValidationCodes.CLAIMANT_SURNAME_ALPHA));
		}
		if(!ValidationUtils.isAlphaOnly(claimant.getOtherName())){
			errors.add(new ValidationError(OTHERNAME, ValidationCodes.CLAIMANT_OTHERNAME_ALPHA));
		}
		if(!ValidationUtils.isAlphaOnly(claimant.getTitle())){
			errors.add(new ValidationError(TITLE, ValidationCodes.CLAIMANT_TITLE_ALPHA));
		}
		
		if(!ValidationUtils.isDateValid(claimant.getDobDay(), claimant.getDobMonth(), claimant.getDobYear())){
			errors.add(new ValidationError(DOB , ValidationCodes.CLAIMANT_DOB_INVALID));
		}else if(!ValidationUtils.isDateInTheFuture(claimant.getDobDay(), claimant.getDobMonth(), claimant.getDobYear())){
			errors.add(new ValidationError(DOB , ValidationCodes.CLAIMANT_DOB_FUTURE));
		}else if(!ValidationUtils.isDateSixteenYearsAgo(claimant.getDobDay(), claimant.getDobMonth(), claimant.getDobYear())){
			errors.add(new ValidationError(DOB , ValidationCodes.CLAIMANT_DOB_UNDER_16));
		}
		
		return errors;
	}
	
}
