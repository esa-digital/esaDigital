package uk.gov.dwp.esa.validators;

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
import uk.gov.dwp.esa.constants.ContactDetailsConstants;
import uk.gov.dwp.esa.constants.ValidationCodes;
import uk.gov.dwp.esa.model.ContactDetails;
import uk.gov.dwp.esa.validatorHelpers.ValidationError;

@RunWith(MockitoJUnitRunner.class)
public class ContactDetailsValidatorTest {

	@InjectMocks
	ContactDetailsValidator contactDetailsValidator = new ContactDetailsValidator();

	@Mock
	ContactDetails contactDetails;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this.getClass());

		when(contactDetails.getAddressLine1()).thenReturn("Address Line 1");
		when(contactDetails.getAddressLine2()).thenReturn("Address Line 2");
		when(contactDetails.getAddressLine3()).thenReturn("Address Line 3");
		when(contactDetails.getAddressLine4()).thenReturn("Address Line 4");
		when(contactDetails.getPhoneNumber()).thenReturn("0744388393");
		when(contactDetails.getOtherNumber()).thenReturn("0744388388");
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
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE1.value(),
				ValidationCodes.CONTACT_DETAILS_ADDRESS1_EMPTY);
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine2IsEmpty() {

		when(contactDetails.getAddressLine2()).thenReturn("");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE2.value(),
				ValidationCodes.CONTACT_DETAILS_ADDRESS2_EMPTY);
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine1HasMoreThan27Chars() {

		when(contactDetails.getAddressLine1()).thenReturn("abcdefghijklmnopqrstuvwxyzabcdef");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE1.value(),
				ValidationCodes.CONTACT_DETAILS_ADDRESS1_TOO_LONG);
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine1HasValidChars() {

		when(contactDetails.getAddressLine1()).thenReturn("Address Line 1 >");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE1.value(),
				ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE1_INVALIDCHARS);
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine2HasMoreThan27Chars() {

		when(contactDetails.getAddressLine2()).thenReturn("abcdefghijklmnopqrstuvwxyzabcdef");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE2.value(),
				ValidationCodes.CONTACT_DETAILS_ADDRESS2_TOO_LONG);
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine2HasValidChars() {

		when(contactDetails.getAddressLine2()).thenReturn("Address Line 2 ^");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE2.value(),
				ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE2_INVALIDCHARS);
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine3HasMoreThan27Chars() {

		when(contactDetails.getAddressLine3()).thenReturn("abcdefghijklmnopqrstuvwxyzabcdef");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE3.value(),
				ValidationCodes.CONTACT_DETAILS_ADDRESS3_TOO_LONG);
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine3HasValidChars() {

		when(contactDetails.getAddressLine3()).thenReturn("Address Line 2 *");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE3.value(),
				ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE3_INVALIDCHARS);
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine4HasMoreThan27Chars() {

		when(contactDetails.getAddressLine4()).thenReturn("abcdefghijklmnopqrstuvwxyzabcdef");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE4.value(),
				ValidationCodes.CONTACT_DETAILS_ADDRESS4_TOO_LONG);
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine4HasValidChars() {

		when(contactDetails.getAddressLine4()).thenReturn("Address Line 4 %");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE4.value(),
				ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE4_INVALIDCHARS);
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenPostCodeIsEmpty() {

		when(contactDetails.getPostCode()).thenReturn("");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.POSTCODE.value(),
				ValidationCodes.CONTACT_DETAILS_POSTCODE_EMPTY);
		Assert.assertEquals(1, errors.size());

	}
	
	@Test
	public void testPostcodeHasMoreThan8Chars() {

		when(contactDetails.getPostCode()).thenReturn("NE6          5LU");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.POSTCODE.value(),
				ValidationCodes.CONTACT_DETAILS_POSTCODE_TOO_LONG);
		Assert.assertEquals(2, errors.size());

	}
	
	@Test
	public void testPhoneNumberHasMoreThan20Chars() {

		when(contactDetails.getPhoneNumber()).thenReturn("012345678912345678901");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.PHONENUMBER.value(),
				ValidationCodes.CONTACT_DETAILS_PHONENUMBER_TOO_LONG);
		Assert.assertEquals(1, errors.size());

	}
	
	@Test
	public void testPhoneNumberHasValidChars() {

		when(contactDetails.getPhoneNumber()).thenReturn("ABC");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.PHONENUMBER.value(),
				ValidationCodes.CONTACT_DETAILS_PHONENUMBER_INVALID);
		Assert.assertEquals(1, errors.size());

	}

	
	@Test
	public void testOtherNumberHasMoreThan20Chars() {

		when(contactDetails.getOtherNumber()).thenReturn("012345678912345678901");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.PHONENUMBER.value(),
				ValidationCodes.CONTACT_DETAILS_OTHERNUMBER_TOO_LONG);
		Assert.assertEquals(1, errors.size());

	}
	
	@Test
	public void testOtherNumberHasValidChars() {

		when(contactDetails.getOtherNumber()).thenReturn("ABC");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.PHONENUMBER.value(),
				ValidationCodes.CONTACT_DETAILS_OTHERNUMBER_INVALID);
		Assert.assertEquals(1, errors.size());

	}
	
	@Test
	public void testEmailMaxLength() {

		when(contactDetails.getEmail()).thenReturn("a.a@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.com");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.EMAIL.value(),
				ValidationCodes.CONTACT_EMAIL_TOO_LONG);
		Assert.assertEquals(1, errors.size());

	}
	
	@Test
	public void testEmailValid() {

		when(contactDetails.getEmail()).thenReturn("a.a.com");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		ValidationError ve = new ValidationError(ContactDetailsConstants.EMAIL.value(),
				ValidationCodes.CONTACT_EMAIL_INVALID);
		Assert.assertEquals(1, errors.size());

	}
	
}
