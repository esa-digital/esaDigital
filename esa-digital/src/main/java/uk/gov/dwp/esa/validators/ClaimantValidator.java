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
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.Claimant;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;
import uk.gov.dwp.esa.validatorHelpers.ValidationUtils;

@Component
public class ClaimantValidator implements Validator {

	private static final int maxLength = 27;
	
	@Autowired
    private MessageSource messageSource;
	/*
	 * This method is used to validate the basic claimant properties 
	 * gathered from front end.
	 * 
	 * @Name : validateClaimant
	 * @ReturnType : List of ValidationError
	 * @Params : Claimant
	 */
	protected List<ValidationError> validateClaimant(Claimant claimant){
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if(claimant==null){
			claimant = new Claimant();
		}
		//check mandatory fields
		//This is a title validation - checks if empty. If not empty, checks length (!>27)
		if(ValidationUtils.isEmpty(claimant.getTitle())){
			ValidationError titleValidation = new ValidationError(ClaimantConstants.TITLE.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_TITLE_EMPTY,null,Locale.ENGLISH));
			errors.add(titleValidation );
		}else{
			if(ValidationUtils.isStringLengthOver(claimant.getTitle(), maxLength )){
				errors.add(new ValidationError(ClaimantConstants.TITLE.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_TITLE_TOO_LONG,null,Locale.ENGLISH)));
			}
			// Checks if title is alpha only
			if(!ValidationUtils.isAlphaOnly(claimant.getTitle())){
				errors.add(new ValidationError(ClaimantConstants.TITLE.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_TITLE_ALPHA,null,Locale.ENGLISH)));
			}
		}
			
		
		if(ValidationUtils.isEmpty(claimant.getFirstName())){
			ValidationError firstNameValidation = new ValidationError(ClaimantConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_FIRSTNAME_EMPTY,null,Locale.ENGLISH));
			errors.add(firstNameValidation );
		}else{
			if(ValidationUtils.isStringLengthOver(claimant.getFirstName(), maxLength )){
				errors.add(new ValidationError(ClaimantConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_FIRST_NAME_TOO_LONG,null,Locale.ENGLISH)));
			}
						
			if(!ValidationUtils.isAlphaOnly(claimant.getFirstName())){
				errors.add(new ValidationError(ClaimantConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_FIRSTNAME_ALPHA,null,Locale.ENGLISH)));
			}
			
			
		}
		
		if(ValidationUtils.isEmpty(claimant.getSurname())){
			ValidationError surnameValidation = new ValidationError(ClaimantConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_SURNAME_EMPTY,null,Locale.ENGLISH));
			errors.add(surnameValidation);
		}else{
			if(ValidationUtils.isStringLengthOver(claimant.getSurname(), maxLength )){
				errors.add(new ValidationError(ClaimantConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_SURNAME_TOO_LONG,null,Locale.ENGLISH)));
			}
			
			if(!ValidationUtils.isAlphaOnly(claimant.getSurname())){
				errors.add(new ValidationError(ClaimantConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_SURNAME_ALPHA,null,Locale.ENGLISH)));
			}
			
		}
			
		// Checks if othername is not empty and stringlength is >27 chars.If so, returns error
		if(claimant.getOtherName()!=null && !claimant.getOtherName().equals("") ){
			if(ValidationUtils.isStringLengthOver(claimant.getOtherName(), maxLength)){
				errors.add(new ValidationError(ClaimantConstants.OTHERNAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_MIDDLE_NAME_TOO_LONG,null,Locale.ENGLISH)));
			}

			if(!ValidationUtils.isAlphaOnly(claimant.getOtherName())){
				errors.add(new ValidationError(ClaimantConstants.OTHERNAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_OTHERNAME_ALPHA,null,Locale.ENGLISH)));
			}
		}

		if(!ValidationUtils.isDateValid(claimant.getDobDay(), claimant.getDobMonth(), claimant.getDobYear())){
			errors.add(new ValidationError(ClaimantConstants.DOB.value() , messageSource.getMessage(ValidationCodes.CLAIMANT_DOB_INVALID,null,Locale.ENGLISH)));
		}else if(ValidationUtils.isDateInTheFuture(claimant.getDobDay(), claimant.getDobMonth(), claimant.getDobYear())){
			errors.add(new ValidationError(ClaimantConstants.DOB.value() , messageSource.getMessage(ValidationCodes.CLAIMANT_DOB_FUTURE,null,Locale.ENGLISH)));
		}else if(!ValidationUtils.isDateSixteenYearsAgo(claimant.getDobDay(), claimant.getDobMonth(), claimant.getDobYear())){
			errors.add(new ValidationError(ClaimantConstants.DOB.value() , messageSource.getMessage(ValidationCodes.CLAIMANT_DOB_UNDER_16,null,Locale.ENGLISH)));
		}
		
		if(ValidationUtils.isEmpty(claimant.getNino())){
			ValidationError ninoValidation = new ValidationError(ClaimantConstants.NINO.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_NINO_EMPTY,null,Locale.ENGLISH));
			errors.add(ninoValidation);
		}else if(!ValidationUtils.isValidNino(claimant.getNino())){
			ValidationError ninoValidation = new ValidationError(ClaimantConstants.NINO.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_NINO_NOTVALID,null,Locale.ENGLISH));
			errors.add(ninoValidation);
		}
		
		return errors;
	}
	@Override
	public boolean supports(Class clazz) {
		return Claimant.class.equals(clazz);
	}
	
	@Override
	public void validate(Object claimant, Errors errors) {
		
		List<ValidationError> validationErrors = this.validateClaimant((Claimant) claimant);
		
		for(ValidationError error :validationErrors){
			errors.rejectValue(error.getFieldId(), error.getErrorMessage());
		}
		
	}
	
		
}
