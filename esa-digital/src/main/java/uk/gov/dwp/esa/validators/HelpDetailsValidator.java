package uk.gov.dwp.esa.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import uk.gov.dwp.esa.constants.HelpDetailsConstants;
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.HelpDetails;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;
import uk.gov.dwp.esa.validatorHelpers.ValidationUtils;

public class HelpDetailsValidator implements Validator{

	private static final int maxLengthCommon = 27;
	private static final int maxLengthReason = 50;
	private static final int maxLengthPostCode = 8;
	private static final int maxLengthPhone = 11;

	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean supports(Class<?> clazz) {
		return HelpDetails.class.equals(clazz);
	}

	@Override
	public void validate(Object helpDetails, Errors errors) {

		List<ValidationError> listOfValidationErrors = this.validateHelpDetails((HelpDetails) helpDetails);

		for(ValidationError error: listOfValidationErrors){
			errors.rejectValue(error.getFieldId(), error.getErrorMessage());
		}
	}

	protected List<ValidationError> validateHelpDetails(HelpDetails helpDetails){
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if(helpDetails == null){
			helpDetails = new HelpDetails();
		}

		if(helpDetails.isGettingHelp()){
			if(ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getPersonName())){
				errors.add(new ValidationError(HelpDetailsConstants.HELPPERSONNAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_PERSONNAME_EMPTY,null, Locale.ENGLISH)));
			}else{
				if(ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getPersonName(), maxLengthCommon)){
					errors.add(new ValidationError(HelpDetailsConstants.HELPPERSONNAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_PERSONNAME_TOO_LONG,null, Locale.ENGLISH)));
				}
				if(!ValidationUtils.isAlphaOnly(helpDetails.getThirdPartyDetails().getPersonName())){
					errors.add(new ValidationError(HelpDetailsConstants.HELPPERSONNAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_PERSONNAME_ALPHA,null, Locale.ENGLISH)));
				}
			}

			if(ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getPersonRelation())){
				errors.add(new ValidationError(HelpDetailsConstants.HELPPERSONRELATION.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_PERSONRELATION_EMPTY,null, Locale.ENGLISH)));
			}else{
				if(ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getPersonRelation(), maxLengthCommon)){
					errors.add(new ValidationError(HelpDetailsConstants.HELPPERSONRELATION.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_PERSONRELATION_TOO_LONG,null, Locale.ENGLISH)));
				}
				if(!ValidationUtils.isAlphaOnly(helpDetails.getThirdPartyDetails().getPersonRelation())){
					errors.add(new ValidationError(HelpDetailsConstants.HELPPERSONRELATION.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_PERSONRELATION_ALPHA,null, Locale.ENGLISH)));
				}
			}

			if(ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getReasonForHelp())){
				errors.add(new ValidationError(HelpDetailsConstants.REASONFORHELP.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_REASONFORHELP_ALPHA,null, Locale.ENGLISH)));
			}else{
				if(ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getReasonForHelp(), maxLengthReason)){
					errors.add(new ValidationError(HelpDetailsConstants.REASONFORHELP.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_REASONFORHELP_ALPHA,null, Locale.ENGLISH)));
				}
				if(!ValidationUtils.isAlphaOnly(helpDetails.getThirdPartyDetails().getReasonForHelp())){
					errors.add(new ValidationError(HelpDetailsConstants.REASONFORHELP.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_REASONFORHELP_ALPHA,null, Locale.ENGLISH)));
				}
			}
		}else if (helpDetails.isApplyingOnBehalfOfSomeoneElse()) {
			if(helpDetails.getOnBehalfOfSomeoneElseOptions().isRegisteredPowerAttorney() || helpDetails.getOnBehalfOfSomeoneElseOptions().isCannotManageAffairs()
					|| helpDetails.getOnBehalfOfSomeoneElseOptions().isYourDeputy() || helpDetails.getOnBehalfOfSomeoneElseOptions().isOther()){

				errors.addAll(validateCommonDetails(helpDetails));

			}else if(helpDetails.getOnBehalfOfSomeoneElseOptions().isRegisteredAppointee()){
				if(ValidationUtils.isEmpty(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getFirstName())){
					errors.add(new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_FIRSTNAME_EMPTY, null, Locale.ENGLISH)));
				}else{
					if(ValidationUtils.isStringLengthOver(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getFirstName(), maxLengthCommon)){
						errors.add(new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_FIRST_NAME_TOO_LONG, null, Locale.ENGLISH)));
					}
					if(!ValidationUtils.isAlphaOnlyWithHyphen(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getFirstName())){
						errors.add(new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_FIRSTNAME_ALPHA_WITH_HYPHEN, null, Locale.ENGLISH)));
					}

				}
				if(ValidationUtils.isEmpty(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getSurname())){
					errors.add(new ValidationError(HelpDetailsConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_SURNAME_EMPTY, null, Locale.ENGLISH)));
				}else{
					if(ValidationUtils.isStringLengthOver(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getSurname(), maxLengthCommon)){
						errors.add(new ValidationError(HelpDetailsConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_SURNAME_TOO_LONG, null, Locale.ENGLISH)));
					}
					if(!ValidationUtils.isAlphaOnlyWithHyphen(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getSurname())){
						errors.add(new ValidationError(HelpDetailsConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_SURNAME_ALPHA_WITH_HYPHEN, null, Locale.ENGLISH)));
					}

				}
			}
		}

		return errors;
	}

	private List<ValidationError> validateCommonDetails(HelpDetails helpDetails){
		List<ValidationError> listErrors = new ArrayList<ValidationError>();

		if(ValidationUtils.isEmpty(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getTitle())){
			listErrors.add(new ValidationError(HelpDetailsConstants.TITLE.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_TITLE_EMPTY, null,Locale.ENGLISH)));
		}else{
			if(ValidationUtils.isStringLengthOver(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getTitle(), maxLengthCommon)){
				listErrors.add(new ValidationError(HelpDetailsConstants.TITLE.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_TITLE_TOO_LONG, null,Locale.ENGLISH)));
			}
			if(!ValidationUtils.isAlphaOnly(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getTitle())){
				listErrors.add(new ValidationError(HelpDetailsConstants.TITLE.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_TITLE_ALPHA, null,Locale.ENGLISH)));
			}

		}

		if(ValidationUtils.isEmpty(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getFirstName())){
			listErrors.add(new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_FIRSTNAME_EMPTY, null,Locale.ENGLISH)));
		}else{
			if(ValidationUtils.isStringLengthOver(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getFirstName(), maxLengthCommon)){
				listErrors.add(new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_FIRST_NAME_TOO_LONG, null,Locale.ENGLISH)));
			}
			if(!ValidationUtils.isAlphaOnlyWithHyphen(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getFirstName())){
				listErrors.add(new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_FIRSTNAME_ALPHA_WITH_HYPHEN, null,Locale.ENGLISH)));
			}

		}
		if(ValidationUtils.isEmpty(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getSurname())){
			listErrors.add(new ValidationError(HelpDetailsConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_SURNAME_EMPTY, null, Locale.ENGLISH)));
		}else{
			if(ValidationUtils.isStringLengthOver(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getSurname(), maxLengthCommon)){
				listErrors.add(new ValidationError(HelpDetailsConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_SURNAME_TOO_LONG, null, Locale.ENGLISH)));
			}
			if(!ValidationUtils.isAlphaOnlyWithHyphen(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getSurname())){
				listErrors.add(new ValidationError(HelpDetailsConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_SURNAME_ALPHA_WITH_HYPHEN, null, Locale.ENGLISH)));
			}

		}
		if(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getOtherName()!=null && !helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getOtherName().equals("") ){
			if(ValidationUtils.isStringLengthOver(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getOtherName(), maxLengthCommon)){
				listErrors.add(new ValidationError(HelpDetailsConstants.OTHERNAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_OTHERNAME_TOO_LONG, null, Locale.ENGLISH)));
			}

			if(!ValidationUtils.isAlphaOnlyWithHyphen(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getOtherName())){
				listErrors.add(new ValidationError(HelpDetailsConstants.OTHERNAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_OTHERNAME_ALPHA_WITH_HYPHEN, null, Locale.ENGLISH)));
			}
		}

		if(!ValidationUtils.isDateValid(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getDobDay(), helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getDobMonth(), helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getDobYear())){
			listErrors.add(new ValidationError(HelpDetailsConstants.DOB.value() , messageSource.getMessage(ValidationCodes.HELPDETAILS_DOB_INVALID,null,Locale.ENGLISH)));
		}else if(ValidationUtils.isDateInTheFuture(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getDobDay(), helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getDobMonth(), helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getDobYear())){
			listErrors.add(new ValidationError(HelpDetailsConstants.DOB.value() , messageSource.getMessage(ValidationCodes.HELPDETAILS_DOB_FUTURE,null,Locale.ENGLISH)));
		}

		if(ValidationUtils.isEmpty(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getNino())){
			listErrors.add(new ValidationError(HelpDetailsConstants.NINO.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_NINO_EMPTY, null, Locale.ENGLISH)));
		}else if(!ValidationUtils.isValidNino(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getNino())){
			listErrors.add(new ValidationError(HelpDetailsConstants.NINO.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_NINO_NOTVALID, null, Locale.ENGLISH)));
		}

		if(ValidationUtils.isEmpty(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getAddLine1())){
			listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE1.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE1_EMPTY, null, Locale.ENGLISH)));
		}else{
			if(ValidationUtils.isStringLengthOver(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getAddLine1(),maxLengthCommon)){
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE1.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE1_TOO_LONG, null, Locale.ENGLISH)));
			}
			if(!ValidationUtils.isAlphaOnly(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getAddLine1())){
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE1.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA, null, Locale.ENGLISH)));
			}
		}


		if(ValidationUtils.isEmpty(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getAddLine2())){
			listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE2.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE2_EMPTY, null, Locale.ENGLISH)));
		}else{
			if(ValidationUtils.isStringLengthOver(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getAddLine2(), maxLengthCommon )){
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE2.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE2_TOO_LONG, null, Locale.ENGLISH)));
			}
			if(!ValidationUtils.isAlphaOnly(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getAddLine2())){
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE2.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA, null, Locale.ENGLISH)));
			}
		}


		if (helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getAddLine3() != null && !helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getAddLine3().equals("")) {
			if (ValidationUtils.isStringLengthOver(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getAddLine3(), maxLengthCommon)) {
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE3.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE3_TOO_LONG, null, Locale.ENGLISH)));
			}

			if (!ValidationUtils.isAlphaOnly(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getAddLine3())) {
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE3.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA, null, Locale.ENGLISH)));
			}
		}

		if (helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getAddLine4() != null && !helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getAddLine4().equals("")) {
			if (ValidationUtils.isStringLengthOver(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getAddLine4(), maxLengthCommon)) {
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE4.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE4_TOO_LONG, null, Locale.ENGLISH)));
			}

			if (!ValidationUtils.isAlphaOnly(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getAddLine4())) {
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE4.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA, null, Locale.ENGLISH)));
			}
		}



		if (ValidationUtils.isEmpty(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getPostCode())) {
			listErrors.add(new ValidationError(HelpDetailsConstants.POSTCODE.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_POSTCODE_EMPTY, null, Locale.ENGLISH)));
		} else {
			if (ValidationUtils.isStringLengthOver(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getPostCode(), maxLengthPostCode)) {
				listErrors.add(new ValidationError(HelpDetailsConstants.POSTCODE.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_POSTCODE_TOO_LONG, null, Locale.ENGLISH)));
			}

				if (!ValidationUtils.isValidPostcode(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getPostCode())) {
					listErrors.add(new ValidationError(HelpDetailsConstants.POSTCODE.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_POSTCODE_INVALID, null, Locale.ENGLISH)));
			}
		}

		if(!ValidationUtils.isEmpty(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getTelNumber())){
			if(ValidationUtils.isStringLengthOver(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getTelNumber(), maxLengthPhone)){
				listErrors.add(new ValidationError(HelpDetailsConstants.TEL_NUMBER.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_TELEPHONE_TOO_LONG, null, Locale.ENGLISH)));
			}	
			if(!ValidationUtils.isTelephoneOnly(helpDetails.getOnBehalfOfSomeoneElseOptions().getBehalfType().getTelNumber())){
				listErrors.add(new ValidationError(HelpDetailsConstants.TEL_NUMBER.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_TELEPHONE_NUMERIC, null, Locale.ENGLISH)));
			}
		}


		return listErrors;
	}
}
