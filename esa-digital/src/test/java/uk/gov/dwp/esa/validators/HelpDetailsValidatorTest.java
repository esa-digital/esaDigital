package uk.gov.dwp.esa.validators;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import junit.framework.Assert;
import uk.gov.dwp.esa.constants.HelpDetailsConstants;
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.BehalfOptions;
import uk.gov.dwp.esa.model.BehalfType;
import uk.gov.dwp.esa.model.GettingHelp;
import uk.gov.dwp.esa.model.HelpDetails;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;

@RunWith(MockitoJUnitRunner.class)
public class HelpDetailsValidatorTest {
	
	@InjectMocks
	HelpDetailsValidator helpDetailsValidator = new HelpDetailsValidator();
	
	@Mock
	HelpDetails helpDetails;
	
	@Mock
	GettingHelp thirdPartyDetails;
	
	@Mock
	BehalfOptions behalfOptions;
	
	@Mock
	BehalfType behalfType;
	
	@Mock
	MessageSource messageSource;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this.getClass());
		when(behalfType.getTitle()).thenReturn("ms");
		when(behalfType.getFirstName()).thenReturn("test");
		when(behalfType.getSurname()).thenReturn("test");
		when(behalfType.getOtherName()).thenReturn("othertest");
		when(behalfType.getDobDay()).thenReturn("01");
		when(behalfType.getDobMonth()).thenReturn("01");
		when(behalfType.getDobYear()).thenReturn("1996");
		when(behalfType.getNino()).thenReturn("ab000001d");
		when(behalfType.getTelNumber()).thenReturn("1234567890");
		when(behalfType.getPostCode()).thenReturn("LS7 5DQ");
		when(behalfType.getAddLine1()).thenReturn("ABC");
		when(behalfType.getAddLine2()).thenReturn("DEF");
		when(behalfType.getAddLine3()).thenReturn("tyu");
		when(behalfType.getAddLine4()).thenReturn("ghgj");
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(thirdPartyDetails.getPersonName()).thenReturn("testname");
		when(thirdPartyDetails.getPersonRelation()).thenReturn("father");
		when(thirdPartyDetails.getReasonForHelp()).thenReturn("Unable to fill");
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		
	}
	
	@Test
	public void testIsFillingYourselfValidation(){
		when(helpDetails.isFillingByYourself()).thenReturn(true);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(0, listValidationErrors.size());
	}
	
	@Test
	public void testThirdPartyValidation(){
		when(helpDetails.isGettingHelp()).thenReturn(true);
		when(thirdPartyDetails.getPersonName()).thenReturn("testname");
		when(thirdPartyDetails.getPersonRelation()).thenReturn("father");
		when(thirdPartyDetails.getReasonForHelp()).thenReturn("Unable to fill");
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(0, listValidationErrors.size());
	}
	
	@Test
	public void testNoThirdPartyDetailsValidation(){
		when(helpDetails.isGettingHelp()).thenReturn(true);
		when(thirdPartyDetails.getPersonName()).thenReturn(null);
		when(thirdPartyDetails.getPersonRelation()).thenReturn(null);
		when(thirdPartyDetails.getReasonForHelp()).thenReturn(null);
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(3, listValidationErrors.size());
	}

	@Test
	public void testThirdPartyDetailsSizeValidation(){
		when(helpDetails.isGettingHelp()).thenReturn(true);
		when(thirdPartyDetails.getPersonName()).thenReturn("abcdefghijklmnopqrstuvwxyzab");
		when(thirdPartyDetails.getPersonRelation()).thenReturn("abcdefghijklmnopqrstuvwxyzab");
		when(thirdPartyDetails.getReasonForHelp()).thenReturn("abcdeghijklmnopqrstuvwxyzababcdeghijklmnopqrstuvwxyzab");
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(3, listValidationErrors.size());
	}
	
	@Test
	public void testThirdPartyDetailsNotAlphaValidation(){
		when(helpDetails.isGettingHelp()).thenReturn(true);
		when(thirdPartyDetails.getPersonName()).thenReturn("abcd4556@7& *&");
		when(thirdPartyDetails.getPersonRelation()).thenReturn("12345");
		when(thirdPartyDetails.getReasonForHelp()).thenReturn("GH^78()");
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(3, listValidationErrors.size());
	}
	
	@Test
	public void testOnBehalfOfRegisteredAppointeeDetailsValidation(){
		when(behalfType.getFirstName()).thenReturn("test");
		when(behalfType.getSurname()).thenReturn("test");
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(behalfOptions.isRegisteredAppointee()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(0, listValidationErrors.size());
	}
	
	@Test
	public void testOnBehalfOfRegisteredAppointeeNoDetailsValidation(){
		when(behalfType.getFirstName()).thenReturn(null);
		when(behalfType.getSurname()).thenReturn(null);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(behalfOptions.isRegisteredAppointee()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(2, listValidationErrors.size());
	}
	
	@Test
	public void testOnBehalfOfRegisteredAppointeeDetailsLengthValidation(){
		when(behalfType.getFirstName()).thenReturn("abcdefghijklmnopqrstuvwxyzab");
		when(behalfType.getSurname()).thenReturn("abcdefghijklmnopqrstuvwxyzab");
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(behalfOptions.isRegisteredAppointee()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(2, listValidationErrors.size());
	}
	
	@Test
	public void testOnBehalfOfRegisteredAppointeeDetailsAlphaValidation(){
		when(behalfType.getFirstName()).thenReturn("abv 563943*&7");
		when(behalfType.getSurname()).thenReturn("1452GHIuYGYi");
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(behalfOptions.isRegisteredAppointee()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(2, listValidationErrors.size());
	}
	
	@Test
	public void testOnBehalfOfCommonOptionsDetailsValidation(){
		when(behalfType.getTitle()).thenReturn("ms");
		when(behalfType.getFirstName()).thenReturn("test");
		when(behalfType.getSurname()).thenReturn("test");
		when(behalfType.getOtherName()).thenReturn("othertest");
		when(behalfType.getDobDay()).thenReturn("01");
		when(behalfType.getDobMonth()).thenReturn("01");
		when(behalfType.getDobYear()).thenReturn("1996");
		when(behalfType.getNino()).thenReturn("ab000001d");
		when(behalfType.getTelNumber()).thenReturn("1234567890");
		when(behalfType.getPostCode()).thenReturn("LS7 5DQ");
		when(behalfType.getAddLine1()).thenReturn("ABC");
		when(behalfType.getAddLine2()).thenReturn("DEF");
		when(behalfType.getAddLine3()).thenReturn("tyu");
		when(behalfType.getAddLine4()).thenReturn("ghgj");
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(behalfOptions.isCannotManageAffairs()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(0, listValidationErrors.size());
	}
	
	@Test
	public void testOnBehalfOfCommonOptionsNoDetailsValidation(){
		when(behalfType.getTitle()).thenReturn(null);
		when(behalfType.getFirstName()).thenReturn(null);
		when(behalfType.getSurname()).thenReturn(null);
		when(behalfType.getOtherName()).thenReturn(null);
		when(behalfType.getDobDay()).thenReturn(null);
		when(behalfType.getDobMonth()).thenReturn(null);
		when(behalfType.getDobYear()).thenReturn(null);
		when(behalfType.getNino()).thenReturn(null);
		when(behalfType.getTelNumber()).thenReturn(null);
		when(behalfType.getPostCode()).thenReturn(null);
		when(behalfType.getAddLine1()).thenReturn(null);
		when(behalfType.getAddLine2()).thenReturn(null);
		when(behalfType.getAddLine3()).thenReturn(null);
		when(behalfType.getAddLine4()).thenReturn(null);
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(behalfOptions.isCannotManageAffairs()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(8, listValidationErrors.size());
	}
	
	@Test
	public void testBehalfTypeTitleLengthValidation(){
		when(behalfType.getTitle()).thenReturn("abcdefghijklmnopqrstuvwxyzabc");
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(behalfOptions.isCannotManageAffairs()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.TITLE.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_TITLE_TOO_LONG, null,Locale.ENGLISH));
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testBehalfTypeTitleAlphaValidation(){
		when(behalfType.getTitle()).thenReturn("abc@%C123");
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(behalfOptions.isCannotManageAffairs()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.TITLE.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_TITLE_ALPHA, null,Locale.ENGLISH));
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testBehalfTypeFirstNameLengthValidation(){
		when(behalfType.getFirstName()).thenReturn("abcdefghijklmnopqrstuvwxyzabc");
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(behalfOptions.isCannotManageAffairs()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_FIRST_NAME_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testBehalfTypeFirstNameAlphaValidation(){
		when(behalfType.getFirstName()).thenReturn("abcdefghijklmn45474757abc");
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(behalfOptions.isCannotManageAffairs()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_FIRSTNAME_ALPHA_WITH_HYPHEN, null,Locale.ENGLISH));
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testBehalfTypeSurNameLengthValidation(){
		when(behalfType.getSurname()).thenReturn("abcdefghijhgkhgpqrstuvwxyzabc");
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(behalfOptions.isCannotManageAffairs()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_SURNAME_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testBehalfTypeSurNameAlphaValidation(){
		when(behalfType.getSurname()).thenReturn("abcdef$%^jklmn45474757abc");
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(behalfOptions.isCannotManageAffairs()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_SURNAME_ALPHA_WITH_HYPHEN, null,Locale.ENGLISH));
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testBehalfTypeOtherNameLengthValidation(){
		when(behalfType.getOtherName()).thenReturn("abcdefghijhgkhgpqrstuvwxyzabc");
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(behalfOptions.isOther()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_OTHERNAME_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testBehalfTypeOtherNameAlphaValidation(){
		when(behalfType.getOtherName()).thenReturn("abcdef$%^jklmn45474757abc");
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(behalfOptions.isYourDeputy()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_OTHERNAME_ALPHA_WITH_HYPHEN, null,Locale.ENGLISH));
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testDOBMandatoryNullValidation() {
		when(behalfType.getDobDay()).thenReturn(null);
		when(behalfType.getDobMonth()).thenReturn(null);
		when(behalfType.getDobYear()).thenReturn(null);
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors =  helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.DOB.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_DOB_INVALID,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testDOBFutureValidation() {
		when(behalfType.getDobDay()).thenReturn("1");
		when(behalfType.getDobMonth()).thenReturn("1");
		when(behalfType.getDobYear()).thenReturn("2019");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors =  helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.DOB.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_DOB_FUTURE,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testNinoNotValid() {
		when(behalfType.getNino()).thenReturn("AB02376e73");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors =  helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.NINO.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_NINO_NOTVALID,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testPostCodeIsEmpty() {
		when(behalfType.getPostCode()).thenReturn("");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.POSTCODE.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_POSTCODE_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testPostCodeIsNull() {
		when(behalfType.getPostCode()).thenReturn(null);
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.POSTCODE.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_POSTCODE_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}

	
	@Test
	public void testPostCodeIsNotValid() {
		when(behalfType.getPostCode()).thenReturn("LS! 678");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.POSTCODE.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_POSTCODE_INVALID,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testPostCodeLength() {
		when(behalfType.getPostCode()).thenReturn("LS! 678 6799887665");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.POSTCODE.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_POSTCODE_INVALID,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
		
	@Test
	public void testGpTelNumberIsOver20Chars(){
		when(behalfType.getTelNumber()).thenReturn("123456789012345678901");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.TEL_NUMBER.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_TELEPHONE_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testGpTelNumberIsNotValidPhoneNumber(){
		when(behalfType.getTelNumber()).thenReturn("+442767!&57");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.TEL_NUMBER.value(),messageSource.getMessage(ValidationCodes.HELPDETAILS_TELEPHONE_INVALID, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
		
	
	@Test
	public void testAddressLine1IsEmptyString() {
		when(behalfType.getAddLine1()).thenReturn("");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE1.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE1_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testAddressLine1IsNull() {
		when(behalfType.getAddLine1()).thenReturn("");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE1.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE1_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testAddressLine1More27Chars() {
		when(behalfType.getAddLine1()).thenReturn("ahduoahwydouqwdajlkshdlajsdhkjahdskahsdjk");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE1.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE1_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testAddressLine1NotAlphaNumeric() {
		when(behalfType.getAddLine1()).thenReturn("ahduoahwy$&%&kahsdjk");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE1.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}

	
	@Test
	public void testaddressLine2IsEmptyString() {
		when(behalfType.getAddLine2()).thenReturn("");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE2.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE2_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testaddressLine2IsNull() {
		when(behalfType.getAddLine2()).thenReturn(null);
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE2.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE2_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testaddressLine2More27Chars() {
		when(behalfType.getAddLine2()).thenReturn("ahduoahwydouqwdajlkshdlajsdhkjahdskahsdjk");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE2.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE2_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testaddressLine2NotAlphaNumeric() {
		when(behalfType.getAddLine2()).thenReturn("ahduoahwy$&%&kahsdjk");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE2.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	
	@Test
	public void testaddressLine3More27Chars(){
		when(behalfType.getAddLine3()).thenReturn("ahduoahwydouqwdajlkshdlajsdhkjahdskahsdjk");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE3.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE3_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testaddressLine3NotAlphaNumeric() {
		when(behalfType.getAddLine3()).thenReturn("ahduoahwy$&%&kshsdjk");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE3.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size() );
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}

	
	@Test
	public void testaddressLine4More27Chars(){
		when(behalfType.getAddLine4()).thenReturn("ahduoahwydouqwdajlkshdlajsdhkjahdskahsdjk");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE4.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE4_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testaddressLine4NotAlphaNumeric() {
		when(behalfType.getAddLine4()).thenReturn("ahduoahwy$&%&kskahsdjk");
		when(behalfOptions.isRegisteredPowerAttorney()).thenReturn(true);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(helpDetails.isApplyingOnBehalfOfSomeoneElse()).thenReturn(true);
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE4.value(), messageSource.getMessage(ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size() );
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
}
