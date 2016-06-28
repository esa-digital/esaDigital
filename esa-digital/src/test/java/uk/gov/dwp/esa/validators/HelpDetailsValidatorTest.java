/*package uk.gov.dwp.esa.validators;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import junit.framework.Assert;
import uk.gov.dwp.esa.constants.HelpDetailsConstants;
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.HelpDetails;
import uk.gov.dwp.esa.model.HelpDetailsType;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;

@RunWith(MockitoJUnitRunner.class)
public class HelpDetailsValidatorTest {
	
	@InjectMocks
	HelpDetailsValidator helpDetailsValidator = new HelpDetailsValidator();
	
	@Mock
	HelpDetails helpDetails;
	
	@Mock
	HelpDetailsType thirdPartyDetails;
	

	
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this.getClass());
		when(thirdPartyDetails.getTitle()).thenReturn("ms");
		when(thirdPartyDetails.getFirstName()).thenReturn("test");
		when(thirdPartyDetails.getSurname()).thenReturn("test");
		when(thirdPartyDetails.getOtherName()).thenReturn("othertest");
		when(thirdPartyDetails.getDobDay()).thenReturn("01");
		when(thirdPartyDetails.getDobMonth()).thenReturn("01");
		when(thirdPartyDetails.getDobYear()).thenReturn("1996");
		when(thirdPartyDetails.getNino()).thenReturn("ab000001d");
		when(thirdPartyDetails.getTelNumber()).thenReturn("07741587433");
		when(thirdPartyDetails.getPostCode()).thenReturn("LS7 5DQ");
		when(thirdPartyDetails.getAddLine1()).thenReturn("ABC");
		when(thirdPartyDetails.getAddLine2()).thenReturn("DEF");
		when(thirdPartyDetails.getAddLine3()).thenReturn("tyu");
		when(thirdPartyDetails.getAddLine4()).thenReturn("ghgj");
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getPersonName()).thenReturn("testname");
		when(thirdPartyDetails.getPersonRelation()).thenReturn("father");
		when(thirdPartyDetails.getReasonForHelp()).thenReturn("Unable to fill");
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		
	}
	
	@Test
	public void testIsFillingYourselfValidation(){
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.SELF_TYPE.value());
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(0, listValidationErrors.size());
	}
	
	@Test
	public void testThirdPartyValidation(){
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.THIRD_PARTY_HELP_TYPE.value());
		when(thirdPartyDetails.getPersonName()).thenReturn("testname");
		when(thirdPartyDetails.getPersonRelation()).thenReturn("father");
		when(thirdPartyDetails.getReasonForHelp()).thenReturn("Unable to fill");
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(0, listValidationErrors.size());
	}
	
	@Test
	public void testNoThirdPartyDetailsValidation(){
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.THIRD_PARTY_HELP_TYPE.value());
		when(thirdPartyDetails.getPersonName()).thenReturn(null);
		when(thirdPartyDetails.getPersonRelation()).thenReturn(null);
		when(thirdPartyDetails.getReasonForHelp()).thenReturn(null);
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(3, listValidationErrors.size());
	}

	@Test
	public void testThirdPartyDetailsSizeValidation(){
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.THIRD_PARTY_HELP_TYPE.value());
		when(thirdPartyDetails.getPersonName()).thenReturn("abcdefghijklmnopqrstuvwxyzab");
		when(thirdPartyDetails.getPersonRelation()).thenReturn("abcdefghijklmnopqrstuvwxyzab");
		when(thirdPartyDetails.getReasonForHelp()).thenReturn("abcdeghijklmnopqrstuvwxyzababcdeghijklmnopqrstuvwxyzab");
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(3, listValidationErrors.size());
	}
	
	
	@Test
	public void testWhenNoOptionSelected(){
		when(helpDetails.getHelpDetailsType()).thenReturn("");
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(1, listValidationErrors.size());
	}
	
	@Test
	public void testThirdPartyDetailsInvalidValidation(){
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.THIRD_PARTY_HELP_TYPE.value());
		when(thirdPartyDetails.getPersonName()).thenReturn(".- ghhkgfttrdf");
		when(thirdPartyDetails.getPersonRelation()).thenReturn("12<>345");
		when(thirdPartyDetails.getReasonForHelp()).thenReturn("GH^78()");
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(3, listValidationErrors.size());
	}
	
	@Test
	public void testOnBehalfOfErrorWhenTypeNotSelected(){
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("");
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(1, listValidationErrors.size());
	}
	
	@Test
	public void testOnBehalfOfRegisteredAppointeeDetailsValidation(){
		when(thirdPartyDetails.getFirstName()).thenReturn("test");
		when(thirdPartyDetails.getSurname()).thenReturn("test");
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("dummy");
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(0, listValidationErrors.size());
	}
	
	@Test
	public void testOnBehalfOfRegisteredAppointeeNoDetailsValidation(){
		when(thirdPartyDetails.getFirstName()).thenReturn(null);
		when(thirdPartyDetails.getSurname()).thenReturn(null);
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("dummy");
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(2, listValidationErrors.size());
	}
	
	@Test
	public void testOnBehalfOfRegisteredAppointeeDetailsLengthValidation(){
		when(thirdPartyDetails.getFirstName()).thenReturn("abcdefghijklmnopqrstuvwxyzab");
		when(thirdPartyDetails.getSurname()).thenReturn("abcdefghijklmnopqrstuvwxyzab");
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
				when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("dummy");
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(2, listValidationErrors.size());
	}
	
	@Test
	public void testOnBehalfOfRegisteredAppointeeDetailsInvalidValidation(){
		when(thirdPartyDetails.getFirstName()).thenReturn("abv 563943*&7");
		when(thirdPartyDetails.getSurname()).thenReturn("1452GHIuYGYi");
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
				when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("dummy");
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(2, listValidationErrors.size());
	}
	
	@Test
	public void testOnBehalfOfCommonOptionsDetailsValidation(){
		when(thirdPartyDetails.getTitle()).thenReturn("ms");
		when(thirdPartyDetails.getFirstName()).thenReturn("test");
		when(thirdPartyDetails.getSurname()).thenReturn("test");
		when(thirdPartyDetails.getOtherName()).thenReturn("othertest");
		when(thirdPartyDetails.getDobDay()).thenReturn("01");
		when(thirdPartyDetails.getDobMonth()).thenReturn("01");
		when(thirdPartyDetails.getDobYear()).thenReturn("1996");
		when(thirdPartyDetails.getNino()).thenReturn("ab000001d");
		when(thirdPartyDetails.getTelNumber()).thenReturn("01234567890");
		when(thirdPartyDetails.getPostCode()).thenReturn("LS7 5DQ");
		when(thirdPartyDetails.getAddLine1()).thenReturn("ABC");
		when(thirdPartyDetails.getAddLine2()).thenReturn("DEF");
		when(thirdPartyDetails.getAddLine3()).thenReturn("tyu");
		when(thirdPartyDetails.getAddLine4()).thenReturn("ghgj");
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("dummy");
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(0, listValidationErrors.size());
	}
	
	@Test
	public void testOnBehalfOfCommonOptionsNoDetailsValidation(){
		when(thirdPartyDetails.getTitle()).thenReturn(null);
		when(thirdPartyDetails.getFirstName()).thenReturn(null);
		when(thirdPartyDetails.getSurname()).thenReturn(null);
		when(thirdPartyDetails.getOtherName()).thenReturn(null);
		when(thirdPartyDetails.getDobDay()).thenReturn(null);
		when(thirdPartyDetails.getDobMonth()).thenReturn(null);
		when(thirdPartyDetails.getDobYear()).thenReturn(null);
		when(thirdPartyDetails.getNino()).thenReturn(null);
		when(thirdPartyDetails.getTelNumber()).thenReturn(null);
		when(thirdPartyDetails.getPostCode()).thenReturn(null);
		when(thirdPartyDetails.getAddLine1()).thenReturn(null);
		when(thirdPartyDetails.getAddLine2()).thenReturn(null);
		when(thirdPartyDetails.getAddLine3()).thenReturn(null);
		when(thirdPartyDetails.getAddLine4()).thenReturn(null);
				when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("dummy");
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(8, listValidationErrors.size());
	}
	
	@Test
	public void testthirdPartyDetailsTitleLengthValidation(){
		when(thirdPartyDetails.getTitle()).thenReturn("abcdefghijklmnopqrstuvwxyzabc");
				when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("dummy");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.TITLE.value(), ValidationCodes.HELPDETAILS_TITLE_TOO_LONG);
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testthirdPartyDetailsTitleAlphaValidation(){
		when(thirdPartyDetails.getTitle()).thenReturn("abc@%C123");
				when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("dummy");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.TITLE.value(), ValidationCodes.HELPDETAILS_TITLE_ALPHA);
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testthirdPartyDetailsFirstNameLengthValidation(){
		when(thirdPartyDetails.getFirstName()).thenReturn("abcdefghijklmnopqrstuvwxyzabc");
				when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("dummy");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.SURNAME.value(), ValidationCodes.HELPDETAILS_FIRST_NAME_TOO_LONG);
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testthirdPartyDetailsFirstNameAlphaValidation(){
		when(thirdPartyDetails.getFirstName()).thenReturn("abcdefghijklmn45474757abc");
				when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("dummy");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), ValidationCodes.HELPDETAILS_FIRSTNAME_ALPHA_WITH_HYPHEN);
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testthirdPartyDetailsSurNameLengthValidation(){
		when(thirdPartyDetails.getSurname()).thenReturn("abcdefghijhgkhgpqrstuvwxyzabc");
				when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("dummy");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.SURNAME.value(), ValidationCodes.HELPDETAILS_SURNAME_TOO_LONG);
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testthirdPartyDetailsSurNameAlphaValidation(){
		when(thirdPartyDetails.getSurname()).thenReturn("abcdef$%^jklmn45474757abc");
				when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("dummy");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), ValidationCodes.HELPDETAILS_SURNAME_ALPHA_WITH_HYPHEN);
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testthirdPartyDetailsOtherNameLengthValidation(){
		when(thirdPartyDetails.getOtherName()).thenReturn("abcdefghijhgkhgpqrstuvwxyzabc");
				when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("dummy");;
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.SURNAME.value(), ValidationCodes.HELPDETAILS_OTHERNAME_TOO_LONG);
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testthirdPartyDetailsOtherNameAlphaValidation(){
		when(thirdPartyDetails.getOtherName()).thenReturn("abcdef$%^jklmn45474757abc");
				when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("deputy");;
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError validationError = new ValidationError(HelpDetailsConstants.FIRST_NAME.value(), ValidationCodes.HELPDETAILS_OTHERNAME_ALPHA_WITH_HYPHEN);
		Assert.assertEquals(1, listValidationErrors.size());
		Assert.assertEquals(listValidationErrors.get(0).getErrorMessage(), validationError.getErrorMessage());
	}
	
	@Test
	public void testDOBMandatoryNullValidation() {
		when(thirdPartyDetails.getDobDay()).thenReturn(null);
		when(thirdPartyDetails.getDobMonth()).thenReturn(null);
		when(thirdPartyDetails.getDobYear()).thenReturn(null);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors =  helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.DOB.value(), ValidationCodes.HELPDETAILS_DOB_INVALID);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testDOBFutureValidation() {
		when(thirdPartyDetails.getDobDay()).thenReturn("1");
		when(thirdPartyDetails.getDobMonth()).thenReturn("1");
		when(thirdPartyDetails.getDobYear()).thenReturn("2019");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors =  helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.DOB.value(), ValidationCodes.HELPDETAILS_DOB_FUTURE);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testNinoNotValid() {
		when(thirdPartyDetails.getNino()).thenReturn("AB02376e73");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors =  helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.NINO.value(), ValidationCodes.HELPDETAILS_NINO_NOTVALID);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testPostCodeIsEmpty() {
		when(thirdPartyDetails.getPostCode()).thenReturn("");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.POSTCODE.value(),ValidationCodes.HELPDETAILS_POSTCODE_EMPTY);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testPostCodeIsNull() {
		when(thirdPartyDetails.getPostCode()).thenReturn(null);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.POSTCODE.value(),ValidationCodes.HELPDETAILS_POSTCODE_EMPTY);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}

	
	@Test
	public void testPostCodeIsNotValid() {
		when(thirdPartyDetails.getPostCode()).thenReturn("LS! 678");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.POSTCODE.value(),ValidationCodes.HELPDETAILS_POSTCODE_INVALID);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testPostCodeLength() {
		when(thirdPartyDetails.getPostCode()).thenReturn("LS! 678 6799887665");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.POSTCODE.value(),ValidationCodes.HELPDETAILS_POSTCODE_INVALID);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
		
	@Test
	public void testGpTelNumberIsOver20Chars(){
		when(thirdPartyDetails.getTelNumber()).thenReturn("0123456789012345678901");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.TEL_NUMBER.value(),ValidationCodes.HELPDETAILS_TELEPHONE_TOO_LONG);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testGpTelNumberIsNotValidPhoneNumber(){
		when(thirdPartyDetails.getTelNumber()).thenReturn("+442767!&57");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.TEL_NUMBER.value(),ValidationCodes.HELPDETAILS_TELEPHONE_INVALID);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
		
	
	@Test
	public void testAddressLine1IsEmptyString() {
		when(thirdPartyDetails.getAddLine1()).thenReturn("");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE1.value(), ValidationCodes.HELPDETAILS_ADDRESS_LINE1_EMPTY);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testAddressLine1IsNull() {
		when(thirdPartyDetails.getAddLine1()).thenReturn("");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE1.value(), ValidationCodes.HELPDETAILS_ADDRESS_LINE1_EMPTY);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testAddressLine1More27Chars() {
		when(thirdPartyDetails.getAddLine1()).thenReturn("ahduoahwydouqwdajlkshdlajsdhkjahdskahsdjk");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE1.value(), ValidationCodes.HELPDETAILS_ADDRESS_LINE1_TOO_LONG);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testAddressLine1NotAlphaNumeric() {
		when(thirdPartyDetails.getAddLine1()).thenReturn("ahduoahwy$&%&kahsdjk");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE1.value(), ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}

	
	@Test
	public void testaddressLine2IsEmptyString() {
		when(thirdPartyDetails.getAddLine2()).thenReturn("");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE2.value(), ValidationCodes.HELPDETAILS_ADDRESS_LINE2_EMPTY);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testaddressLine2IsNull() {
		when(thirdPartyDetails.getAddLine2()).thenReturn(null);
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE2.value(), ValidationCodes.HELPDETAILS_ADDRESS_LINE2_EMPTY);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testaddressLine2More27Chars() {
		when(thirdPartyDetails.getAddLine2()).thenReturn("ahduoahwydouqwdajlkshdlajsdhkjahdskahsdjk");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE2.value(), ValidationCodes.HELPDETAILS_ADDRESS_LINE2_TOO_LONG);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testaddressLine2NotAlphaNumeric() {
		when(thirdPartyDetails.getAddLine2()).thenReturn("ahduoahwy$&%&kahsdjk");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE2.value(), ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	
	@Test
	public void testaddressLine3More27Chars(){
		when(thirdPartyDetails.getAddLine3()).thenReturn("ahduoahwydouqwdajlkshdlajsdhkjahdskahsdjk");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE3.value(), ValidationCodes.HELPDETAILS_ADDRESS_LINE3_TOO_LONG);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testaddressLine3NotAlphaNumeric() {
		when(thirdPartyDetails.getAddLine3()).thenReturn("ahduoahwy$&%&kshsdjk");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE3.value(), ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA);
		Assert.assertEquals(1, errors.size() );
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}

	
	@Test
	public void testaddressLine4More27Chars(){
		when(thirdPartyDetails.getAddLine4()).thenReturn("ahduoahwydouqwdajlkshdlajsdhkjahdskahsdjk");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE4.value(), ValidationCodes.HELPDETAILS_ADDRESS_LINE4_TOO_LONG);
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testaddressLine4NotAlphaNumeric() {
		when(thirdPartyDetails.getAddLine4()).thenReturn("ahduoahwy$&%&kskahsdjk");
		when(thirdPartyDetails.getBehalfOfType()).thenReturn("attorney");
		
		when(helpDetails.getHelpDetailsType()).thenReturn(HelpDetailsConstants.BEHALF_TYPE.value());
		List<ValidationError> errors = helpDetailsValidator.validateHelpDetails(helpDetails);
		ValidationError ve = new ValidationError(HelpDetailsConstants.ADDRESS_LINE4.value(), ValidationCodes.HELPDETAILS_ADDRESS_LINE_ALPHA);
		Assert.assertEquals(1, errors.size() );
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
}
*/