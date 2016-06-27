package uk.gov.dwp.esa.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import uk.gov.dwp.esa.constants.HelpDetailsConstants;
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.HelpDetails;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;
import uk.gov.dwp.esa.validatorHelpers.ValidationUtils;

@Component
public class HelpDetailsValidator implements Validator{

	private static final int maxLengthCommon = 27;
	private static final int maxLengthReason = 50;
	private static final int maxLengthPhone = 20;


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

	protected List<ValidationError> validateHelpDetails(HelpDetails helpDetails) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (ValidationUtils.isEmpty(helpDetails.getHelpDetailsType())) {
			errors.add(new ValidationError(HelpDetailsConstants.HELPDETAILSTYPE.value(),
					ValidationCodes.HELPDETAILS_TYPE_EMPTY));
		} else {
			if (helpDetails.getHelpDetailsType().equals(HelpDetailsConstants.THIRD_PARTY_HELP_TYPE.value())) {

				if (ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getPersonName())) {
					errors.add(new ValidationError(HelpDetailsConstants.HELPPERSONNAME.value(),
							ValidationCodes.HELPDETAILS_PERSONNAME_EMPTY));
				} else {
					if (ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getPersonName(),
							maxLengthCommon)) {
						errors.add(new ValidationError(HelpDetailsConstants.HELPPERSONNAME.value(),
								ValidationCodes.HELPDETAILS_PERSONNAME_TOO_LONG));
					}
					if (!ValidationUtils.isValidName(helpDetails.getThirdPartyDetails().getPersonName())) {
						errors.add(new ValidationError(HelpDetailsConstants.HELPPERSONNAME.value(),
								ValidationCodes.HELPDETAILS_PERSONNAME_ALPHA));
					}
				}

				if (ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getPersonRelation())) {
					errors.add(new ValidationError(HelpDetailsConstants.HELPPERSONRELATION.value(),
							ValidationCodes.HELPDETAILS_PERSONRELATION_EMPTY));
				} else {
					if (ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getPersonRelation(),
							maxLengthCommon)) {
						errors.add(new ValidationError(HelpDetailsConstants.HELPPERSONRELATION.value(),
								ValidationCodes.HELPDETAILS_PERSONRELATION_TOO_LONG));
					}
					if (!ValidationUtils.isValidFreeText(helpDetails.getThirdPartyDetails().getPersonRelation())) {
						errors.add(new ValidationError(HelpDetailsConstants.HELPPERSONRELATION.value(),
								ValidationCodes.HELPDETAILS_PERSONRELATION_ALPHA));
					}
				}

				if (ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getReasonForHelp())) {
					errors.add(new ValidationError(HelpDetailsConstants.REASONFORHELP.value(),
							ValidationCodes.HELPDETAILS_REASONFORHELP_ALPHA));
				} else {
					if (ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getReasonForHelp(),
							maxLengthReason)) {
						errors.add(new ValidationError(HelpDetailsConstants.REASONFORHELP.value(),
								ValidationCodes.HELPDETAILS_REASONFORHELP_ALPHA));
					}
					if (!ValidationUtils.isValidFreeText(helpDetails.getThirdPartyDetails().getReasonForHelp())) {
						errors.add(new ValidationError(HelpDetailsConstants.REASONFORHELP.value(),
								ValidationCodes.HELPDETAILS_REASONFORHELP_ALPHA));
					}
				}
			} else if (helpDetails.getHelpDetailsType().equals(HelpDetailsConstants.BEHALF_TYPE.value())) {
				if (ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getBehalfOfType())) {
					errors.add(new ValidationError(HelpDetailsConstants.BEHALF_TYPE.value(),
							ValidationCodes.HELPDETAILS_TYPE_EMPTY));

				} else {
					errors.addAll(validateCommonDetails(helpDetails));
				}
			}
		}

		return errors;

	}
	

	private List<ValidationError> validateCommonDetails(HelpDetails helpDetails){
		List<ValidationError> listErrors = new ArrayList<ValidationError>();

		if(ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getTitle())){
			listErrors.add(new ValidationError(HelpDetailsConstants.TITLE.value(), ValidationCodes.HELPDETAILS_TITLE_EMPTY));
		}else{
			if(ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getTitle(), maxLengthCommon)){
				listErrors.add(new ValidationError(HelpDetailsConstants.TITLE.value(), ValidationCodes.HELPDETAILS_TITLE_TOO_LONG));
			}
			if(!ValidationUtils.isValidName(helpDetails.getThirdPartyDetails().getTitle())){
				listErrors.add(new ValidationError(HelpDetailsConstants.TITLE.value(), ValidationCodes.HELPDETAILS_TITLE_ALPHA));
			}

		}

		if(ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getFirstName())){
			listErrors.add(new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), ValidationCodes.HELPDETAILS_FIRSTNAME_EMPTY));
		}else{
			if(ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getFirstName(), maxLengthCommon)){
				listErrors.add(new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), ValidationCodes.HELPDETAILS_FIRST_NAME_TOO_LONG));
			}
			if(!ValidationUtils.isValidName(helpDetails.getThirdPartyDetails().getFirstName())){
				listErrors.add(new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), ValidationCodes.HELPDETAILS_FIRSTNAME_ALPHA_WITH_HYPHEN));
			}

		}
		if(ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getSurname())){
			listErrors.add(new ValidationError(HelpDetailsConstants.SURNAME.value(), ValidationCodes.HELPDETAILS_SURNAME_EMPTY));
		}else{
			if(ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getSurname(), maxLengthCommon)){
				listErrors.add(new ValidationError(HelpDetailsConstants.SURNAME.value(), ValidationCodes.HELPDETAILS_SURNAME_TOO_LONG));
			}
			if(!ValidationUtils.isValidName(helpDetails.getThirdPartyDetails().getSurname())){
				listErrors.add(new ValidationError(HelpDetailsConstants.SURNAME.value(), ValidationCodes.HELPDETAILS_SURNAME_ALPHA_WITH_HYPHEN));
			}

		}
		if(helpDetails.getThirdPartyDetails().getOtherName()!=null && !helpDetails.getThirdPartyDetails().getOtherName().equals("") ){
			if(ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getOtherName(), maxLengthCommon)){
				listErrors.add(new ValidationError(HelpDetailsConstants.OTHERNAME.value(), ValidationCodes.HELPDETAILS_OTHERNAME_TOO_LONG));
			}

			if(!ValidationUtils.isValidName(helpDetails.getThirdPartyDetails().getOtherName())){
				listErrors.add(new ValidationError(HelpDetailsConstants.OTHERNAME.value(), ValidationCodes.HELPDETAILS_OTHERNAME_ALPHA_WITH_HYPHEN));
			}
		}

		if(!ValidationUtils.isDateValid(helpDetails.getThirdPartyDetails().getDobDay(), helpDetails.getThirdPartyDetails().getDobMonth(), helpDetails.getThirdPartyDetails().getDobYear())){
			listErrors.add(new ValidationError(HelpDetailsConstants.DOB.value() , ValidationCodes.HELPDETAILS_DOB_INVALID));
		}else if(ValidationUtils.isDateInTheFuture(helpDetails.getThirdPartyDetails().getDobDay(), helpDetails.getThirdPartyDetails().getDobMonth(), helpDetails.getThirdPartyDetails().getDobYear())){
			listErrors.add(new ValidationError(HelpDetailsConstants.DOB.value() , ValidationCodes.HELPDETAILS_DOB_FUTURE));
		}

		if(ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getNino())){
			listErrors.add(new ValidationError(HelpDetailsConstants.NINO.value(), ValidationCodes.HELPDETAILS_NINO_EMPTY));
		}else if(!ValidationUtils.isValidNino(helpDetails.getThirdPartyDetails().getNino())){
			listErrors.add(new ValidationError(HelpDetailsConstants.NINO.value(), ValidationCodes.HELPDETAILS_NINO_NOTVALID));
		}

		if(ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getAddLine1())){
			listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE1.value(),ValidationCodes.HELPDETAILS_ADDRESS_LINE1_EMPTY));
		}else{
			if(ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getAddLine1(),maxLengthCommon)){
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE1.value(),ValidationCodes.HELPDETAILS_ADDRESS_LINE1_TOO_LONG));
			}
			if(!ValidationUtils.isValidAddress(helpDetails.getThirdPartyDetails().getAddLine1())){
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE1.value(),ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA));
			}
		}


		if(ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getAddLine2())){
			listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE2.value(),ValidationCodes.HELPDETAILS_ADDRESS_LINE2_EMPTY));
		}else{
			if(ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getAddLine2(), maxLengthCommon )){
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE2.value(),ValidationCodes.HELPDETAILS_ADDRESS_LINE2_TOO_LONG));
			}
			if(!ValidationUtils.isValidAddress(helpDetails.getThirdPartyDetails().getAddLine2())){
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE2.value(),ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA));
			}
		}


		if (helpDetails.getThirdPartyDetails().getAddLine3() != null && !helpDetails.getThirdPartyDetails().getAddLine3().equals("")) {
			if (ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getAddLine3(), maxLengthCommon)) {
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE3.value(),ValidationCodes.HELPDETAILS_ADDRESS_LINE3_TOO_LONG));
			}

			if (!ValidationUtils.isValidAddress(helpDetails.getThirdPartyDetails().getAddLine3())) {
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE3.value(),ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA));
			}
		}

		if (helpDetails.getThirdPartyDetails().getAddLine4() != null && !helpDetails.getThirdPartyDetails().getAddLine4().equals("")) {
			if (ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getAddLine4(), maxLengthCommon)) {
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE4.value(),ValidationCodes.HELPDETAILS_ADDRESS_LINE4_TOO_LONG));
			}

			if (!ValidationUtils.isValidAddress(helpDetails.getThirdPartyDetails().getAddLine4())) {
				listErrors.add(new ValidationError(HelpDetailsConstants.ADDRESS_LINE4.value(),ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA));
			}
		}



		if (ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getPostCode())) {
			listErrors.add(new ValidationError(HelpDetailsConstants.POSTCODE.value(), ValidationCodes.HELPDETAILS_POSTCODE_EMPTY));
		} else {
			if (!ValidationUtils.isValidPostcode(helpDetails.getThirdPartyDetails().getPostCode())) {
				listErrors.add(new ValidationError(HelpDetailsConstants.POSTCODE.value(), ValidationCodes.HELPDETAILS_POSTCODE_INVALID));
			}
		}

		if(!ValidationUtils.isEmpty(helpDetails.getThirdPartyDetails().getTelNumber())){
			if(ValidationUtils.isStringLengthOver(helpDetails.getThirdPartyDetails().getTelNumber(), maxLengthPhone)){
				listErrors.add(new ValidationError(HelpDetailsConstants.TEL_NUMBER.value(), ValidationCodes.HELPDETAILS_TELEPHONE_TOO_LONG));
			}	
			if(!ValidationUtils.isTelephoneOnly(helpDetails.getThirdPartyDetails().getTelNumber())){
				listErrors.add(new ValidationError(HelpDetailsConstants.TEL_NUMBER.value(), ValidationCodes.HELPDETAILS_TELEPHONE_INVALID));
			}
		}
	

		return listErrors;
	}
}
