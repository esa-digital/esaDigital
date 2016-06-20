package uk.gov.dwp.esa.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import uk.gov.dwp.esa.constants.BankDetailsConstants;
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.BankDetails;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;
import uk.gov.dwp.esa.validatorHelpers.ValidationUtils;

@Component
public class BankDetailsValidator implements Validator{
	
	private static final int accHolderMaxLength = 18;
	private static final int sortCodeMaxLength = 6;
	private static final int accountNumberMaxLength = 8;
	private static final int buildingNumberMaxLength = 18;
	
	@Autowired
    private MessageSource messageSource;

	@Override
	public boolean supports(Class<?> clazz) {
		return BankDetails.class.equals(clazz);
	}

	@Override
	public void validate(Object bankDetails, Errors errors) {

		List<ValidationError> validationErrors = validateBankDetails((BankDetails) bankDetails);
		for(ValidationError error :validationErrors){
			errors.rejectValue(error.getFieldId(), error.getErrorMessage());
		}
	}

	protected List<ValidationError> validateBankDetails(BankDetails bankDetails){
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if(bankDetails==null){
			bankDetails = new BankDetails();
		}
		
		if(ValidationUtils.isEmpty(bankDetails.getAcctHolderName())){
			errors.add(new ValidationError(BankDetailsConstants.ACCOUNTHOLDER.value(), messageSource.getMessage(ValidationCodes.BANKDETAILS_ACCOUNTHOLDER_EMPTY, null, Locale.ENGLISH)));
		}else{
			if(ValidationUtils.isStringLengthOver(bankDetails.getAcctHolderName(), accHolderMaxLength)){
				errors.add(new ValidationError(BankDetailsConstants.ACCOUNTHOLDER.value(), messageSource.getMessage(ValidationCodes.BANKDETAILS_ACCOUNTHOLDER_TOO_LONG, null, Locale.ENGLISH)));
			}
			if(!ValidationUtils.isAlphaOnly(bankDetails.getAcctHolderName())){
				errors.add(new ValidationError(BankDetailsConstants.ACCOUNTHOLDER.value(), messageSource.getMessage(ValidationCodes.BANKDETAILS_ACCOUNTHOLDER_ALPHA, null, Locale.ENGLISH)));
			}
		}
		
		if(ValidationUtils.isEmpty(bankDetails.getSortCode())){
			errors.add(new ValidationError(BankDetailsConstants.SORTCODE.value(), messageSource.getMessage(ValidationCodes.BANKDETAILS_SORTCODE_EMPTY, null, Locale.ENGLISH)));
		}else{
			if(ValidationUtils.isStringLengthOver(bankDetails.getSortCode(), sortCodeMaxLength)){
				errors.add(new ValidationError(BankDetailsConstants.SORTCODE.value(), messageSource.getMessage(ValidationCodes.BANKDETAILS_SORTCODE_TOO_LONG, null, Locale.ENGLISH)));
			}
			if(!ValidationUtils.isNumericOnly(bankDetails.getAccountNumber())){
				errors.add(new ValidationError(BankDetailsConstants.ACCOUNTNUMBER.value(), messageSource.getMessage(ValidationCodes.BANKDETAILS_ACCNUMBER_NUMERIC, null, Locale.ENGLISH)));
			}
		}
		
		if(ValidationUtils.isEmpty(bankDetails.getAccountNumber()) && ValidationUtils.isEmpty(bankDetails.getBuildingSociety())){
			errors.add(new ValidationError(BankDetailsConstants.ACCOUNTNUMBER.value(), messageSource.getMessage(ValidationCodes.BANKDETAILS_ACCNUMBER_BUILDINGNUMBER_EMPTY, null, Locale.ENGLISH)));
			errors.add(new ValidationError(BankDetailsConstants.BUILDINGSOCIETY.value(), null));
		}else{
			if(!ValidationUtils.isEmpty(bankDetails.getAccountNumber())){
				if(ValidationUtils.isStringLengthOver(bankDetails.getAccountNumber(), accountNumberMaxLength)){
					errors.add(new ValidationError(BankDetailsConstants.ACCOUNTNUMBER.value(), messageSource.getMessage(ValidationCodes.BANKDETAILS_ACCNUMBER_TOO_LONG, null, Locale.ENGLISH)));
				}
				if(!ValidationUtils.isNumericOnly(bankDetails.getAccountNumber())){
					errors.add(new ValidationError(BankDetailsConstants.ACCOUNTNUMBER.value(), messageSource.getMessage(ValidationCodes.BANKDETAILS_ACCNUMBER_NUMERIC, null, Locale.ENGLISH)));
				}
			}
			if(!ValidationUtils.isEmpty(bankDetails.getBuildingSociety())){
				if(ValidationUtils.isStringLengthOver(bankDetails.getBuildingSociety(), buildingNumberMaxLength)){
					errors.add(new ValidationError(BankDetailsConstants.BUILDINGSOCIETY.value(), messageSource.getMessage(ValidationCodes.BANKDETAILS_BUILDINGNUMBER_TOO_LONG, null, Locale.ENGLISH)));
				}
				if(!ValidationUtils.isAlphaOnly(bankDetails.getBuildingSociety())){
					errors.add(new ValidationError(BankDetailsConstants.BUILDINGSOCIETY.value(), messageSource.getMessage(ValidationCodes.BANKDETAILS_BUILDINGNUMBER_ALPHA, null, Locale.ENGLISH)));
				}
			}
		}
				
		return errors;
	}
	
}
