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
import uk.gov.dwp.esa.constants.ClaimantConstants;
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.Claimant;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;

@RunWith(MockitoJUnitRunner.class)
public class ClaimantValidatorTest {
	
	@InjectMocks
	ClaimantValidator validator = new ClaimantValidator();

	@Mock
	Claimant claimant;
	
	@Mock
	MessageSource messageSource;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this.getClass());
		when(claimant.getTitle()).thenReturn("mr");
		when(claimant.getFirstName()).thenReturn("test first");
		when(claimant.getSurname()).thenReturn("surname");
		when(claimant.getDobDay()).thenReturn("01");
		when(claimant.getDobMonth()).thenReturn("01");
		when(claimant.getDobYear()).thenReturn("1999");
		when(claimant.getNino()).thenReturn("ab123456c");
		
	}
	
	@Test
	public void testClaimantValidation() {
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		
		Assert.assertEquals(0, errors.size());
		
		
	}
	
	@Test
	public void testClaimantValidationWhenClaimantNull() {
		List<ValidationError> errors =  validator.validateClaimant(null);
		//checks all 5 mandatory fields and they all should be errors.
		Assert.assertEquals(5, errors.size());
		
		
	}
	
	/*
	 * 
	 * JUNITS for Claimant TITLE JA060003 
	 * 
	 * 
	 * 
	 * */
	//-----------------------------------------------------------------------------------------------
	@Test
	public void testTitleMoreThan27Validation() {
		when(claimant.getTitle()).thenReturn("abcdefghijklmnopqrstuvwxyzabc");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.TITLE.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_TITLE_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	@Test
	public void testTitleOnlyAlphaValidation() {
		when(claimant.getTitle()).thenReturn("abc@%C123");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.TITLE.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_TITLE_ALPHA,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	@Test
	public void testTitleFreeTextNoNumbersValidation() {
		when(claimant.getTitle()).thenReturn("abc123");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.TITLE.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_TITLE_ALPHA,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	@Test
	public void testTitleMandatoryValidation() {
		when(claimant.getTitle()).thenReturn("");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.TITLE.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_TITLE_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	@Test
	public void testTitleMandatoryNullValidation() {
		when(claimant.getTitle()).thenReturn(null);
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.TITLE.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_TITLE_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	/*
	 * 
	 * JUNITS for Claimant FISTNAME JA060003 
	 * 
	 * 
	 * 
	 * */
	//-----------------------------------------------------------------------------------------------
	@Test
	public void testFirstNameMoreThan27Validation() {
		when(claimant.getFirstName()).thenReturn("abcdefghijklmnopqrstuvwxyzabc");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_FIRST_NAME_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	@Test
	public void testFirstNameOnlyAlphaValidation() {
		when(claimant.getFirstName()).thenReturn("abc@%C123");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_FIRSTNAME_ALPHA,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	@Test
	public void testFirstNameFreeTextNoNumbersValidation() {
		when(claimant.getFirstName()).thenReturn("abc123");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_FIRSTNAME_ALPHA,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	@Test
	public void testFirstNameMandatoryValidation() {
		when(claimant.getFirstName()).thenReturn("");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_FIRSTNAME_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	@Test
	public void testFirstNameMandatoryNullValidation() {
		when(claimant.getFirstName()).thenReturn(null);
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.FIRST_NAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_FIRSTNAME_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	/*
	 * 
	 * JUNITS for Claimant SURNAME JA060003 
	 * 
	 * 
	 * 
	 * */
	//-----------------------------------------------------------------------------------------------
	@Test
	public void testSurNameMoreThan27Validation() {
		when(claimant.getSurname()).thenReturn("abcdefghijklmnopqrstuvwxyzabc");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_SURNAME_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	@Test
	public void testSurNameOnlyAlphaValidation() {
		when(claimant.getSurname()).thenReturn("abc@%C123");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_SURNAME_ALPHA,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	@Test
	public void testSurNameFreeTextNoNumbersValidation() {
		when(claimant.getSurname()).thenReturn("abc123");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_SURNAME_ALPHA,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	@Test
	public void testSurNameMandatoryValidation() {
		when(claimant.getSurname()).thenReturn("");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_SURNAME_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	@Test
	public void testSurNameMandatoryNullValidation() {
		when(claimant.getSurname()).thenReturn(null);
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.SURNAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_SURNAME_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	
	
	
	/*
	 * 
	 * JUNITS for Claimant Other Name JA060003 
	 * 
	 * 
	 * 
	 * */
	//-----------------------------------------------------------------------------------------------
	
	@Test
	public void testOtherNameNotMandatoryValidation() {
		when(claimant.getOtherName()).thenReturn(null);
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		Assert.assertEquals(0, errors.size());
	}
	
	@Test
	public void testOtherNameEmptyValidation() {
		when(claimant.getOtherName()).thenReturn("");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		Assert.assertEquals(0, errors.size());
	}
	
	@Test
	public void testOtherNameAlphaWhenAdded() {
		when(claimant.getOtherName()).thenReturn("abc@%C123");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.OTHERNAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_OTHERNAME_ALPHA,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testOtherNameMoreThan27WhenAdded() {
		when(claimant.getOtherName()).thenReturn("abcdefghijklmnopqrstuvwxyzabc");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.OTHERNAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_MIDDLE_NAME_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testOtherNameFreeTextNoNumbersValidation() {
		when(claimant.getOtherName()).thenReturn("abc123");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.OTHERNAME.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_OTHERNAME_ALPHA,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	/*
	 * 
	 * JUNITS for Claimant DOB JA060003 
	 * 
	 * 
	 * 
	 * */
	//-----------------------------------------------------------------------------------------------
	@Test
	public void testDOBMandatoryValidation() {
		when(claimant.getDobDay()).thenReturn("");
		when(claimant.getDobMonth()).thenReturn("");
		when(claimant.getDobYear()).thenReturn("");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.DOB.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_DOB_INVALID,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
		
	}
	
	@Test
	public void testDOBMandatoryNullValidation() {
		when(claimant.getDobDay()).thenReturn(null);
		when(claimant.getDobMonth()).thenReturn(null);
		when(claimant.getDobYear()).thenReturn(null);
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.DOB.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_DOB_INVALID,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testDOBInFutureValidation() {
		when(claimant.getDobDay()).thenReturn("11");
		when(claimant.getDobMonth()).thenReturn("11");
		when(claimant.getDobYear()).thenReturn("2018");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.DOB.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_DOB_FUTURE,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testDOBBefore1900Validation() {
		when(claimant.getDobDay()).thenReturn("11");
		when(claimant.getDobMonth()).thenReturn("11");
		when(claimant.getDobYear()).thenReturn("1899");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.DOB.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_DOB_INVALID,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testDOBLessThan16Validation() {
		when(claimant.getDobDay()).thenReturn("11");
		when(claimant.getDobMonth()).thenReturn("11");
		when(claimant.getDobYear()).thenReturn("2015");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.DOB.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_DOB_UNDER_16,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	/*
	 * 
	 * JUNITS for Claimant NINO JA060003 
	 * 
	 * 
	 * 
	 * */
	//-----------------------------------------------------------------------------------------------
	@Test
	public void testNinoMandatory() {
		when(claimant.getNino()).thenReturn("");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.NINO.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_NINO_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testNinoNotValid() {
		when(claimant.getNino()).thenReturn("ab123456e");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.NINO.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_NINO_NOTVALID,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
	
	@Test
	public void testNinoNotValid2() {
		when(claimant.getNino()).thenReturn("abc kkd");
		List<ValidationError> errors =  validator.validateClaimant(claimant);
		ValidationError ve = new ValidationError(ClaimantConstants.NINO.value(), messageSource.getMessage(ValidationCodes.CLAIMANT_NINO_NOTVALID,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		Assert.assertEquals(errors.get(0).getErrorMessage(), ve.getErrorMessage());
	}
}
