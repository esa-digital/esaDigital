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
import uk.gov.dwp.esa.constants.GpDetailsConstants;
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.GPDetails;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;

@RunWith(MockitoJUnitRunner.class)
public class GpDetailsValidatorTest {
	
	@InjectMocks
	GpDetailsValidator gpDetailsValidator = new GpDetailsValidator();
	
	@Mock
	GPDetails gpDetails;
	
	@Mock
	MessageSource source;
	
	//Sets up the J unit tests which should pass because the values are valid
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this.getClass());
		when(gpDetails.getDoctorName()).thenReturn("Dr Smith");
		when(gpDetails.getDocAddLine1()).thenReturn("11 Grandville Street");
		when(gpDetails.getDocAddLine2()).thenReturn("London");
		when(gpDetails.getDocAddLine3()).thenReturn("");
		when(gpDetails.getDocAddLine4()).thenReturn("");
		when(gpDetails.getDocPostCode()).thenReturn("LS7 5DQ");
		when(gpDetails.getDocTelNumber()).thenReturn("+44 0115 3721032");	
	}
	
	@Test
	public void testGpDetailsValidation() {
		List<ValidationError> errors =  gpDetailsValidator.validateGPDetails(gpDetails);
		
		Assert.assertEquals(0, errors.size());
		
		
	}
	
	@Test
	public void testGpDetailsValidationWhenGpDetailsNull() {
		List<ValidationError> errors =  gpDetailsValidator.validateGPDetails(null);
		//checks all 5 mandatory fields and they all should be errors.
		Assert.assertEquals(5, errors.size());
	}
	
	// JUNITS FOR GP NAME:

	//Test GP name is less than 27 characters.
	//Test if GP name is more than 27 characters.
	//Test if GP name is null.
	
	//Test GP name is alpha.
	//Test if GP name is non-alpha. 
	
	
	//Test GP name is not empty.
	//Test if GP name is empty.
	
	
	//HOW DOES THIS TEST WORK?
	//IN STEPS:
	// 1) Create a test to test if the GP name field is empty.
	// 2) Mockito mocks the GPDetails class and returns "" for getDoctorName.
	// 3) Mockito mocks the message that is sent back for DOCTOR_NAME_EMPTY to then return value = "dummy".
	// 4) Creates a list of ValidationErrors (called errors) using the GpDetailsValidator class.
	// 5) Creates a new instance of ValidationError and passes in the value of DOCTOR_NAME then returns the message for DOCTOR_NAME_EMPTY (i.e., "dummy").
	// 6) Checks that there is 1 error message (i.e., it is an empty string, hence an error).
	// 7) Checks that the error message, for the first error in the array, is equal to value = "dummy". ?
	
	@Test
	public void testGpNameIsEmptyString() {
		when(gpDetails.getDoctorName()).thenReturn("");
		when(source.getMessage(ValidationCodes.DOCTOR_NAME_EMPTY,null,Locale.ENGLISH)).thenReturn("dummy");
		List <ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_NAME.value(),source.getMessage(ValidationCodes.DOCTOR_NAME_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
		
	}
	
	@Test
	public void testGpNameMandatoryNotNullValidation() {
		when(gpDetails.getDoctorName()).thenReturn(null);
		when(source.getMessage(ValidationCodes.DOCTOR_NAME_EMPTY,null,Locale.ENGLISH)).thenReturn("dummy5");
		List<ValidationError> errors =  gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_NAME.value(), source.getMessage(ValidationCodes.DOCTOR_NAME_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testGpNameMoreThan27Validation() {
		when(gpDetails.getDoctorName()).thenReturn("abcdefghijklmnopqrstuvwxyzabc");
		when(source.getMessage(ValidationCodes.DOCTOR_NAME_TOO_LONG,null,Locale.ENGLISH)).thenReturn("dummy2");
		List<ValidationError> errors =  gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_NAME.value(), source.getMessage(ValidationCodes.DOCTOR_NAME_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	@Test
	public void testGpNameFreeTextNoNumbersValidation() {
		when(gpDetails.getDoctorName()).thenReturn("abc123");
		when(source.getMessage(ValidationCodes.DOCTOR_NAME_ALPHA,null,Locale.ENGLISH)).thenReturn("dummy3");
		List<ValidationError> errors =  gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_NAME.value(), source.getMessage(ValidationCodes.DOCTOR_NAME_ALPHA,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testGpNameAlphaNoSpecialCharactersValidation() {
		when(gpDetails.getDoctorName()).thenReturn("abc!@?%");
		when(source.getMessage(ValidationCodes.DOCTOR_NAME_ALPHA,null,Locale.ENGLISH)).thenReturn("dummy4");
		List<ValidationError> errors =  gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_NAME.value(), source.getMessage(ValidationCodes.DOCTOR_NAME_ALPHA,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	// **JUNITS FOR GP DETAILS ADDRESS LINE 1 ////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	
	//Must not be empty
	//Must be less than 27 characters
	//Must be alphanumeric.
	
	//Test if empty					Y
	//Test if not empty				Y
	//Test if less than 27 chars	Y
	//Test if more than 27 chars	Y
	//Test if alphanumeric			Y
	//Test if not alphanumeric		Y
	//Test if NULL					Y
	
	@Test
	public void testGpAddressLine1IsEmptyString() {
		when(gpDetails.getDocAddLine1()).thenReturn("");
		when(source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE1_EMPTY, null,Locale.ENGLISH)).thenReturn("dummy5");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE1.value(), source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE1_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testGpAddressLine1IsNull() {
		when(gpDetails.getDocAddLine1()).thenReturn(null);
		when(source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE1_EMPTY, null,Locale.ENGLISH)).thenReturn("dummy6");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE1.value(), source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE1_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testGpAddressLine1More27Chars() {
		when(gpDetails.getDocAddLine1()).thenReturn("1234567890123456789012345678");
		when(source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE1_TOO_LONG, null,Locale.ENGLISH)).thenReturn("dummy7");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE1.value(), source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE1_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testGpAddressLine1NotAlphaNumeric() {
		when(gpDetails.getDocAddLine1()).thenReturn("123ABC!@?");
		when(source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE1_ALPHANUMERIC, null,Locale.ENGLISH)).thenReturn("dummy8");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE1.value(), source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE1_ALPHANUMERIC,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
//////////////////////////////////////////////////////////////////////////////////
// **JUNITS FOR GP DETAILS ADDRESS LINE 2 ////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
	
	//Same as address line 1
	
	@Test
	public void testGpAddressLine2IsEmptyString() {
		when(gpDetails.getDocAddLine2()).thenReturn("");
		when(source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE2_EMPTY, null,Locale.ENGLISH)).thenReturn("dummy9");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE2.value(), source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE2_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testGpAddressLine2IsNull() {
		when(gpDetails.getDocAddLine2()).thenReturn(null);
		when(source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE2_EMPTY, null,Locale.ENGLISH)).thenReturn("dummy10");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE2.value(), source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE2_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testGpAddressLine2More27Chars() {
		when(gpDetails.getDocAddLine2()).thenReturn("1234567890123456789012345678");
		when(source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE2_TOO_LONG, null,Locale.ENGLISH)).thenReturn("dummy11");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE2.value(), source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE2_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testGpAddressLine2NotAlphaNumeric() {
		when(gpDetails.getDocAddLine2()).thenReturn("123ABC!@?");
		when(source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE2_ALPHANUMERIC, null,Locale.ENGLISH)).thenReturn("dummy12");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE2.value(), source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE2_ALPHANUMERIC,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
//////////////////////////////////////////////////////////////////////////////////
//**JUNITS FOR GP DETAILS ADDRESS LINE 3 ////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
	
	//Same as lines 1 and 2, but no isEmpty or isNull validation required (as not mandatory question)
	
	@Test
	public void testGpAddressLine3IsEmptyString() {
		when(gpDetails.getDocAddLine3()).thenReturn("");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE3.value(),null);
		Assert.assertEquals(0, errors.size());
	}
	
	@Test
	public void testGpAddressLine3IsNullString() {
		when(gpDetails.getDocAddLine3()).thenReturn(null);
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE3.value(),null);
		Assert.assertEquals(0, errors.size());
	}
	
	@Test
	public void testGpAddressLine3More27Chars(){
		when(gpDetails.getDocAddLine3()).thenReturn("1234567890123456789012345678");
		when(source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE3_TOO_LONG,null,Locale.ENGLISH)).thenReturn("dummy13");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE3.value(),source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE3_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testGpAddressLine3NotAlphaNumeric() {
		when(gpDetails.getDocAddLine3()).thenReturn("ABC123!@£");
		when(source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE3_ALPHANUMERIC, null, Locale.ENGLISH)).thenReturn("dummy14");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE3.value(),source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE3_ALPHANUMERIC,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size() );
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
//////////////////////////////////////////////////////////////////////////////////
//**JUNITS FOR GP DETAILS ADDRESS LINE 4 ////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
	
//Same as line 3
	
	@Test
	public void testGpAddressLine4IsEmptyString() {
		when(gpDetails.getDocAddLine4()).thenReturn("");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE4.value(),null);
		Assert.assertEquals(0, errors.size());
	}
	
	@Test
	public void testGpAddressLine4IsNullString() {
		when(gpDetails.getDocAddLine4()).thenReturn(null);
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE4.value(),null);
		Assert.assertEquals(0, errors.size());
	}
	
	@Test
	public void testGpAddressLine4More27Chars(){
		when(gpDetails.getDocAddLine4()).thenReturn("1234567890123456789012345678");
		when(source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE4_TOO_LONG,null,Locale.ENGLISH)).thenReturn("dummy15");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE4.value(),source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE4_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testGpAddressLine4NotAlphaNumeric() {
		when(gpDetails.getDocAddLine4()).thenReturn("ABC123!@£");
		when(source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE4_ALPHANUMERIC, null, Locale.ENGLISH)).thenReturn("dummy16");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_ADDRESS_LINE4.value(),source.getMessage(ValidationCodes.DOCTOR_ADDRESS_LINE4_ALPHANUMERIC,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size() );
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
//////////////////////////////////////////////////////////////////////////////////
//** JUNITS FOR GP DETAILS POSTCODE ///////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
	
	//Tests:
	
	//1) Test if empty.					Y
	//2) Test if not empty				Y
	//3) Test if null.					Y
	//4) Test if over 8 characters.		Y	
	//5)Test if 8 characters or under.	Y
	//5) Test if not alphanumeric		Y
	//6) Test if alphanumeric			Y
	
	@Test
	public void testGpPostCodeIsEmpty() {
		when(gpDetails.getDocPostCode()).thenReturn("");
		when(source.getMessage(ValidationCodes.DOCTOR_POSTCODE_EMPTY, null, Locale.ENGLISH)).thenReturn("dummy17");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_POSTCODE.value(),source.getMessage(ValidationCodes.DOCTOR_POSTCODE_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testGpPostCodeIsNull() {
		when(gpDetails.getDocPostCode()).thenReturn(null);
		when(source.getMessage(ValidationCodes.DOCTOR_POSTCODE_EMPTY, null, Locale.ENGLISH)).thenReturn("dummy18");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_POSTCODE.value(),source.getMessage(ValidationCodes.DOCTOR_POSTCODE_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}

	
	@Test
	public void testGpPostCodeIsNotValid() {
		when(gpDetails.getDocPostCode()).thenReturn("LS! 3DP");
		when(source.getMessage(ValidationCodes.DOCTOR_POSTCODE_INVALID, null, Locale.ENGLISH)).thenReturn("dummy20");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_POSTCODE.value(),source.getMessage(ValidationCodes.DOCTOR_POSTCODE_INVALID,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
//////////////////////////////////////////////////////////////////////////////////
//**JUNITS FOR GP DETAILS TEL. NUMBER ////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////
	
	//Tests:
	
		//1) Test if empty.					Y
		//2) Test if not empty				Y
		//3) Test if null.					Y
		//4) Test if over 20 characters.	Y	
		//5)Test if 20 characters or under.	Y
		//5) Test if not valid phone no.	N
		//6) Test if valid phone number		Y
	
	@Test
	public void testGpTelNumberIsEmpty(){
		when(gpDetails.getDocTelNumber()).thenReturn("");
		when(source.getMessage(ValidationCodes.DOCTOR_TELEPHONE_EMPTY, null, Locale.ENGLISH)).thenReturn("dummy21");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_TEL_NUMBER.value(),source.getMessage(ValidationCodes.DOCTOR_TELEPHONE_EMPTY, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testGpTelNumberIsNull(){
		when(gpDetails.getDocTelNumber()).thenReturn(null);
		when(source.getMessage(ValidationCodes.DOCTOR_TELEPHONE_EMPTY, null, Locale.ENGLISH)).thenReturn("dummy22");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_TEL_NUMBER.value(),source.getMessage(ValidationCodes.DOCTOR_TELEPHONE_EMPTY, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testGpTelNumberIsOver20Chars(){
		when(gpDetails.getDocTelNumber()).thenReturn("123456789012345678901");
		when(source.getMessage(ValidationCodes.DOCTOR_TELEPHONE_TOO_LONG, null, Locale.ENGLISH)).thenReturn("dummy23");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_TEL_NUMBER.value(),source.getMessage(ValidationCodes.DOCTOR_TELEPHONE_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testGpTelNumberIsNotValidPhoneNumber(){
		when(gpDetails.getDocTelNumber()).thenReturn("+44 0!275 256126");
		when(source.getMessage(ValidationCodes.DOCTOR_TELEPHONE_NUMERIC, null, Locale.ENGLISH)).thenReturn("dummy24");
		List<ValidationError> errors = gpDetailsValidator.validateGPDetails(gpDetails);
		ValidationError ve = new ValidationError(GpDetailsConstants.DOCTOR_TEL_NUMBER.value(),source.getMessage(ValidationCodes.DOCTOR_TELEPHONE_NUMERIC, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
		
		
	}
	
	
	
	
	

