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
import uk.gov.dwp.esa.constants.ContactDetailsConstants;
import uk.gov.dwp.esa.constants.GpDetailsConstants;
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.ContactDetails;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;

@RunWith(MockitoJUnitRunner.class)
public class ContactDetailsDetailsValidatorTest {

	@InjectMocks
	ContactDetailsValidator contactDetailsValidator = new ContactDetailsValidator();

	@Mock
	ContactDetails contactDetails;

	@Mock
	MessageSource source;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this.getClass());

		when(contactDetails.getAddressLine1()).thenReturn("Address Line 1");
		when(contactDetails.getAddressLine2()).thenReturn("Address Line 2");
		when(contactDetails.getAddressLine3()).thenReturn("Address Line 3");
		when(contactDetails.getAddressLine4()).thenReturn("Address Line 4");
		when(contactDetails.getPhoneNumber()).thenReturn("744388393");
		when(contactDetails.getOtherNumber()).thenReturn("744388388");
		when(contactDetails.getEmail()).thenReturn("a.a@a.com");
		when(contactDetails.getPostCode()).thenReturn("NE65LU");

	}

	@Test
	public void testContactDetailsValidation() {
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		Assert.assertEquals(0, errors.size());
	}

	@Test
	public void testContactDetailsWhenAddressLine1IsEmpty() {

		when(contactDetails.getAddressLine1()).thenReturn("");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS1_EMPTY,null,Locale.ENGLISH)).thenReturn("Address Line 1 cannot be null");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE1.value(),source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS1_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		
	}
	
	@Test
	public void testContactDetailsWhenAddressLine2IsEmpty() {

		when(contactDetails.getAddressLine2()).thenReturn("");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS2_EMPTY,null,Locale.ENGLISH)).thenReturn("Address Line 2 cannot be null");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE2.value(),source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS2_EMPTY,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		
	}
	
	@Test
	public void testContactDetailsWhenAddressLine1HasMoreThan27Chars() {

		when(contactDetails.getAddressLine1()).thenReturn("abcdefghijklmnopqrstuvwxyzabcdef");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS1_TOO_LONG,null,Locale.ENGLISH)).thenReturn("Address Line 1 cannot be more than 27 chars in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE1.value(),source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS1_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		
	}
	
	@Test
	public void testContactDetailsWhenAddressLine1HasValidChars() {

		when(contactDetails.getAddressLine1()).thenReturn("Affress Line 1 ?");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE1_ALPHANUMERIC,null,Locale.ENGLISH)).thenReturn("Address Line 1 cannot be more than 27 chars in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE1.value(),source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE1_ALPHANUMERIC,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		
	}
	
	@Test
	public void testContactDetailsWhenAddressLine2HasMoreThan27Chars() {

		when(contactDetails.getAddressLine2()).thenReturn("abcdefghijklmnopqrstuvwxyzabcdef");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS2_TOO_LONG,null,Locale.ENGLISH)).thenReturn("Address Line 2 cannot be more than 27 chars in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE2.value(),source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS2_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		
	}
	
	@Test
	public void testContactDetailsWhenAddressLine2HasValidChars() {

		when(contactDetails.getAddressLine2()).thenReturn("Affress Line 2 ?");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE2_ALPHANUMERIC,null,Locale.ENGLISH)).thenReturn("Address Line 2 cannot be more than 27 chars in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE2.value(),source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE2_ALPHANUMERIC,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		
	}
	
	@Test
	public void testContactDetailsWhenAddressLine3HasMoreThan27Chars() {

		when(contactDetails.getAddressLine3()).thenReturn("abcdefghijklmnopqrstuvwxyzabcdef");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS3_TOO_LONG,null,Locale.ENGLISH)).thenReturn("Address Line 3 cannot be more than 27 chars in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE3.value(),source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS3_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		
	}
	
	@Test
	public void testContactDetailsWhenAddressLine3HasValidChars() {

		when(contactDetails.getAddressLine3()).thenReturn("Affress Line 2 ?");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE3_ALPHANUMERIC,null,Locale.ENGLISH)).thenReturn("Address Line 3 cannot be more than 27 chars in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE3.value(),source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE3_ALPHANUMERIC,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		
	}
	
	@Test
	public void testContactDetailsWhenAddressLine4HasMoreThan27Chars() {

		when(contactDetails.getAddressLine4()).thenReturn("abcdefghijklmnopqrstuvwxyzabcdef");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS4_TOO_LONG,null,Locale.ENGLISH)).thenReturn("Address Line 4 cannot be more than 27 chars in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE4.value(),source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS4_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		
	}
	
	@Test
	public void testContactDetailsWhenAddressLine4HasValidChars() {

		when(contactDetails.getAddressLine4()).thenReturn("Affress Line 4 ?");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE4_ALPHANUMERIC,null,Locale.ENGLISH)).thenReturn("Address Line 4 cannot be more than 27 chars in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE4.value(),source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE4_ALPHANUMERIC,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		
	}
	
	@Test
	public void testContactDetailsWhenPostCodeIsEmpty() {

		when(contactDetails.getPostCode()).thenReturn("");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS4_TOO_LONG,null,Locale.ENGLISH)).thenReturn("Address Line 4 cannot be more than 27 chars in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE4.value(),source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS4_TOO_LONG,null,Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());
		
	}
	

}
