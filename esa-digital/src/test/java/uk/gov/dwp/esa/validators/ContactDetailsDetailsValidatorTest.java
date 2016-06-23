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
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS1_EMPTY, null, Locale.ENGLISH))
				.thenReturn("Address Line 1 cannot be null");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE1.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS1_EMPTY, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine2IsEmpty() {

		when(contactDetails.getAddressLine2()).thenReturn("");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS2_EMPTY, null, Locale.ENGLISH))
				.thenReturn("Address Line 2 cannot be null");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE2.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS2_EMPTY, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine1HasMoreThan27Chars() {

		when(contactDetails.getAddressLine1()).thenReturn("abcdefghijklmnopqrstuvwxyzabcdef");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS1_TOO_LONG, null, Locale.ENGLISH))
				.thenReturn("Address Line 1 cannot be more than 27 chars in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE1.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS1_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine1HasValidChars() {

		when(contactDetails.getAddressLine1()).thenReturn("Address Line 1 >");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE1_INVALIDCHARS, null, Locale.ENGLISH))
				.thenReturn("Address Line 1 does not have valid characters");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE1.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE1_INVALIDCHARS, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine2HasMoreThan27Chars() {

		when(contactDetails.getAddressLine2()).thenReturn("abcdefghijklmnopqrstuvwxyzabcdef");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS2_TOO_LONG, null, Locale.ENGLISH))
				.thenReturn("Address Line 2 cannot be more than 27 chars in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE2.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS2_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine2HasValidChars() {

		when(contactDetails.getAddressLine2()).thenReturn("Address Line 2 ^");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE2_INVALIDCHARS, null, Locale.ENGLISH))
				.thenReturn("Address Line 2 must have valid characters");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE2.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE2_INVALIDCHARS, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine3HasMoreThan27Chars() {

		when(contactDetails.getAddressLine3()).thenReturn("abcdefghijklmnopqrstuvwxyzabcdef");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS3_TOO_LONG, null, Locale.ENGLISH))
				.thenReturn("Address Line 3 cannot be more than 27 chars in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE3.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS3_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine3HasValidChars() {

		when(contactDetails.getAddressLine3()).thenReturn("Address Line 2 *");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE3_INVALIDCHARS, null, Locale.ENGLISH))
				.thenReturn("Address Line 3 must have valid characters");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE3.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE3_INVALIDCHARS, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine4HasMoreThan27Chars() {

		when(contactDetails.getAddressLine4()).thenReturn("abcdefghijklmnopqrstuvwxyzabcdef");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS4_TOO_LONG, null, Locale.ENGLISH))
				.thenReturn("Address Line 4 cannot be more than 27 chars in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE4.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS4_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenAddressLine4HasValidChars() {

		when(contactDetails.getAddressLine4()).thenReturn("Address Line 4 %");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE4_INVALIDCHARS, null, Locale.ENGLISH))
				.thenReturn("Address Line 4 must have valid characters");
		ValidationError ve = new ValidationError(ContactDetailsConstants.ADDRESSLINE4.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_ADDRESS_LINE4_INVALIDCHARS, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}

	@Test
	public void testContactDetailsWhenPostCodeIsEmpty() {

		when(contactDetails.getPostCode()).thenReturn("");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_POSTCODE_EMPTY, null, Locale.ENGLISH))
				.thenReturn("Post code cannot be empty");
		ValidationError ve = new ValidationError(ContactDetailsConstants.POSTCODE.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_POSTCODE_EMPTY, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}
	
	@Test
	public void testPostcodeHasMoreThan8Chars() {

		when(contactDetails.getPostCode()).thenReturn("NE6          5LU");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_POSTCODE_TOO_LONG, null, Locale.ENGLISH))
				.thenReturn("postcode cannot be more than 8 chars in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.POSTCODE.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_POSTCODE_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(2, errors.size());

	}
	
	@Test
	public void testPhoneNumberHasMoreThan20Chars() {

		when(contactDetails.getPhoneNumber()).thenReturn("012345678912345678901");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_PHONENUMBER_TOO_LONG, null, Locale.ENGLISH))
				.thenReturn("postcode should have valid characters");
		ValidationError ve = new ValidationError(ContactDetailsConstants.PHONENUMBER.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_PHONENUMBER_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}
	
	@Test
	public void testPhoneNumberHasValidChars() {

		when(contactDetails.getPhoneNumber()).thenReturn("ABC");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_PHONENUMBER_INVALID, null, Locale.ENGLISH))
				.thenReturn("phone numner should have valid characters");
		ValidationError ve = new ValidationError(ContactDetailsConstants.PHONENUMBER.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_PHONENUMBER_INVALID, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}

	
	@Test
	public void testOtherNumberHasMoreThan20Chars() {

		when(contactDetails.getOtherNumber()).thenReturn("012345678912345678901");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_OTHERNUMBER_TOO_LONG, null, Locale.ENGLISH))
				.thenReturn("Other phone number should only be 20 char length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.PHONENUMBER.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_OTHERNUMBER_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}
	
	@Test
	public void testOtherNumberHasValidChars() {

		when(contactDetails.getOtherNumber()).thenReturn("ABC");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_DETAILS_OTHERNUMBER_INVALID, null, Locale.ENGLISH))
				.thenReturn("Other phone number should have only valid characters");
		ValidationError ve = new ValidationError(ContactDetailsConstants.PHONENUMBER.value(),
				source.getMessage(ValidationCodes.CONTACT_DETAILS_OTHERNUMBER_INVALID, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}
	
	@Test
	public void testEmailMaxLength() {

		when(contactDetails.getEmail()).thenReturn("a.a@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.com");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_EMAIL_TOO_LONG, null, Locale.ENGLISH))
				.thenReturn("Email should be only 50 char in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.EMAIL.value(),
				source.getMessage(ValidationCodes.CONTACT_EMAIL_TOO_LONG, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}
	
	@Test
	public void testEmailValid() {

		when(contactDetails.getEmail()).thenReturn("a.a.com");
		List<ValidationError> errors = contactDetailsValidator.validateContactDetails(contactDetails);
		when(source.getMessage(ValidationCodes.CONTACT_EMAIL_INVALID, null, Locale.ENGLISH))
				.thenReturn("Email should be only 50 char in length");
		ValidationError ve = new ValidationError(ContactDetailsConstants.EMAIL.value(),
				source.getMessage(ValidationCodes.CONTACT_EMAIL_INVALID, null, Locale.ENGLISH));
		Assert.assertEquals(1, errors.size());

	}
	
}
