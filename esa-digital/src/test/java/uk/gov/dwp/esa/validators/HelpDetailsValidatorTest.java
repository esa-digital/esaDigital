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
import org.springframework.context.MessageSource;

import junit.framework.Assert;
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
		// To use for validations
		/*when(behalfType.getTitle()).thenReturn("ms");
		when(behalfType.getFirstName()).thenReturn("test");
		when(behalfType.getSurname()).thenReturn("test");
		when(behalfType.getOtherName()).thenReturn("othertest");
		when(behalfType.getDobDay()).thenReturn("01");
		when(behalfType.getDobMonth()).thenReturn("01");
		when(behalfType.getDobYear()).thenReturn("1996");
		when(behalfType.getNino()).thenReturn("ab000001d");
		when(behalfType.getTelNumber()).thenReturn("+44 1234567890");
		when(behalfType.getPostCode()).thenReturn("NE1233");
		when(behalfType.getAddLine1()).thenReturn("ABC");
		when(behalfType.getAddLine2()).thenReturn("DEF");
		when(behalfType.getAddLine3()).thenReturn("tyu");
		when(behalfType.getAddLine4()).thenReturn("ghgj");
		when(behalfOptions.getBehalfType()).thenReturn(behalfType);
		when(helpDetails.getOnBehalfOfSomeoneElseOptions()).thenReturn(behalfOptions);
		when(thirdPartyDetails.getPersonName()).thenReturn("testname");
		when(thirdPartyDetails.getPersonRelation()).thenReturn("father");
		when(thirdPartyDetails.getReasonForHelp()).thenReturn("Unable to fill");
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);*/
	}
	
	@Test
	public void testHelpDetailsThirdPartyValidation(){
		when(helpDetails.isGettingHelp()).thenReturn(true);
		when(thirdPartyDetails.getPersonName()).thenReturn("testname");
		when(thirdPartyDetails.getPersonRelation()).thenReturn("father");
		when(thirdPartyDetails.getReasonForHelp()).thenReturn("Unable to fill");
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(0, listValidationErrors.size());
	}
	
	@Test
	public void testHelpDetailsNoThirdPartyDetailsValidation(){
		when(helpDetails.isGettingHelp()).thenReturn(true);
		when(thirdPartyDetails.getPersonName()).thenReturn(null);
		when(thirdPartyDetails.getPersonRelation()).thenReturn(null);
		when(thirdPartyDetails.getReasonForHelp()).thenReturn(null);
		when(helpDetails.getThirdPartyDetails()).thenReturn(thirdPartyDetails);
		List<ValidationError> listValidationErrors = helpDetailsValidator.validateHelpDetails(helpDetails);
		Assert.assertEquals(3, listValidationErrors.size());
	}

}
