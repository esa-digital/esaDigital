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

import junit.framework.Assert;
import uk.gov.dwp.esa.constants.AlternateFormatsConstants;
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.AlternativeFormat;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;

@RunWith(MockitoJUnitRunner.class)
public class AlternativeFormatValidatorTest {
	
	@InjectMocks
	AlternativeFormatsValidator alternativeFormatsValidator = new AlternativeFormatsValidator();
	
	@Mock
	AlternativeFormat alternativeFormat;
	

	
	//Sets up the J unit tests which should pass because the values are valid
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this.getClass());
		when(alternativeFormat.getAlternativeFormatYN()).thenReturn("Yes");
		when(alternativeFormat.getAlternateFormatType()).thenReturn("Other");
		when(alternativeFormat.getAlternateFormatOther()).thenReturn("Video, if possible?");
	}
	
	@Test
	public void testAlternativeFormatValidation() {
		List<ValidationError> errors =  alternativeFormatsValidator.validateAlternativeFormats(alternativeFormat);
		
		Assert.assertEquals(0, errors.size());
		
	}
	

	
	//Junit for alternative formats Yes/No
	
	// 1) Test for not empty		Y
	// 2) Test for empty ("")		Y
	
	
	@Test
	public void testAlternativeFormatIsEmpty(){
	when(alternativeFormat.getAlternativeFormatYN()).thenReturn("");
	List <ValidationError> errors = alternativeFormatsValidator.validateAlternativeFormats(alternativeFormat);
	ValidationError ve = new ValidationError(AlternateFormatsConstants.ALTERNATE_FORMAT.value(),ValidationCodes.ALTERNATE_FORMAT_EMPTY);
	Assert.assertEquals(1, errors.size());
	Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	//Junit for alternative format type
	
	// 1) Test for not empty	Y
	// 2) Test for empty("")	Y	
	
	@Test
	public void testAlternativeFormatTypeIsEmpty(){
	when(alternativeFormat.getAlternateFormatType()).thenReturn("");
	List <ValidationError> errors = alternativeFormatsValidator.validateAlternativeFormats(alternativeFormat);
	ValidationError ve = new ValidationError(AlternateFormatsConstants.ALTERNATE_FORMAT_TYPE.value(),ValidationCodes.ALTERNATE_FORMAT_TYPE_EMPTY);
	Assert.assertEquals(1, errors.size());
	Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	
	}
	
	//Junits for alternative format other
	
	// 1) When empty			Y
	// 2) When not empty		Y
	// 3) when too long			Y
	
	@Test
	public void testAlternativeFormatOtherIsEmpty(){
	when(alternativeFormat.getAlternateFormatOther()).thenReturn("");
	List <ValidationError> errors = alternativeFormatsValidator.validateAlternativeFormats(alternativeFormat);
	ValidationError ve = new ValidationError(AlternateFormatsConstants.ALTERNATE_FORMAT_OTHER.value(),ValidationCodes.ALTERNATE_FORMAT_OTHER_EMPTY);
	Assert.assertEquals(1, errors.size());
	Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());
	}
	
	@Test
	public void testAlternativeFormatOtherIsTooLong(){
	when(alternativeFormat.getAlternateFormatOther()).thenReturn("12345678901234567890123456");
	List <ValidationError> errors = alternativeFormatsValidator.validateAlternativeFormats(alternativeFormat);
	ValidationError ve = new ValidationError(AlternateFormatsConstants.ALTERNATE_FORMAT_OTHER.value(),ValidationCodes.ALTERNATE_FORMAT_OTHER_TOO_LONG);
	Assert.assertEquals(1, errors.size());
	Assert.assertEquals(errors.get(0).getErrorMessage(),ve.getErrorMessage());	
	}
	
	} //end of class
	
	
	
	
	

