package uk.gov.dwp.esa.validatorHelpers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import uk.gov.dwp.esa.validatorHelpers.ValidationError;
import uk.gov.dwp.esa.validatorHelpers.ValidationUtils;

public class ValidationUtilsTest {

	/**
     * Tests the method replaceAngleBracketsWithHtmlEntity(..) 
     * @throws Exception
     */
    @Test
    public void testReplaceAngleBracketsWithHtmlEntity() throws Exception {
        String text = "test <html> testing";

        String resultExpected = "test &lt;html&gt; testing";

        Assert.assertEquals(resultExpected, ValidationUtils.replaceAngleBracketsWithHtmlEntity(text));
    }

    /**
     * Tests the isEmpty(..) method
     * @throws Exception
     */
    @Test
    public void testIsEmpty() throws Exception {
        String empty = "";
        String nullString = null;
        String notEmpty = "Testing";

        Assert.assertTrue(ValidationUtils.isEmpty(empty));
        Assert.assertTrue(ValidationUtils.isEmpty(nullString));
        Assert.assertFalse(ValidationUtils.isEmpty(notEmpty));
    }
    
    /**
     * Tests the isDateValid(..) method
     * @throws Exception
     */
    @Test
    public void testIsDateValid() throws Exception {
        String day = "09", month = "10", year = "1967";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));

        day = "3";
        month = "4";
        year = "1967";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));

        // month as 3 letters
        day = "8";
        month = "mar";
        year = "2019";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));
        day = "8";
        month = "MAR";
        year = "2019";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));
        day = "8";
        month = "Dec";
        year = "2019";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));
        day = "8";
        month = "APR";
        year = "2019";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));

        // month as full word
        day = "8";
        month = "march";
        year = "2011";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));
        day = "4";
        month = "NOVEMBER";
        year = "1987";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));
        day = "19";
        month = "DECEMber";
        year = "1989";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));
        
        

        //All month possibilities
        String[] months = new String[] { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug",
                "sep", "sept", "oct", "nov", "dec" };
        for (int counter=0; counter < months.length ; counter++){
            Assert.assertTrue(ValidationUtils.isDateValid("9", months[counter], "1985"));
        }

        months = new String[] { "january", "february", "march", "april", "may", "june",
                "july", "august", "september", "october", "november", "december" };
        for (int counter=0; counter < months.length ; counter++){
            Assert.assertTrue(ValidationUtils.isDateValid("9", months[counter], "1985"));
        }
        
        // Year as 2 digits
        day = "8";
        month = "mar";
        year = "1919";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));
        day = "8";
        month = "10";
        year = "1960";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));
        day = "8";
        month = "NOV";
        year = "1987";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));

        // Other valid variations of the month
        day = "8";
        month = "sept";
        year = "1967";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));

        // leap year valid date
        day = "29";
        month = "feb";
        year = "2012";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));
        // leap year invalid date
        day = "29";
        month = "feb";
        year = "2015";
        Assert.assertFalse(ValidationUtils.isDateValid(day, month, year));

        day = "1";
        month = "08";
        year = "2000";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));
        day = "1";
        month = "AUG";
        year = "2000";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));
        day = "1";
        month = "AUGUST";
        year = "2003";
        Assert.assertTrue(ValidationUtils.isDateValid(day, month, year));

        // The below are not considered valid dates
        day = "15";
        month = "3";
        year = "5";
        Assert.assertFalse(ValidationUtils.isDateValid(day, month, year));
        day = "1fe";
        month = "ge";
        year = "kj";
        Assert.assertFalse(ValidationUtils.isDateValid(day, month, year));
        day = "8";
        month = "mar";
        year = "193";
        Assert.assertFalse(ValidationUtils.isDateValid(day, month, year));
        day = "8";
        month = "mar";
        year = "87";
        Assert.assertFalse(ValidationUtils.isDateValid(day, month, year));
        day = "19";
        month = "DECEMber";
        year = "1899";
        Assert.assertFalse(ValidationUtils.isDateValid(day, month, year));
        day = "00";
        month = "DECEMber";
        year = "0000";
        Assert.assertFalse(ValidationUtils.isDateValid(day, month, year));
        day = "00";
        month = "00";
        year = "0000";
        Assert.assertFalse(ValidationUtils.isDateValid(day, month, year));
    }

    /**
     * Tests the isDateSixteenYearsAgo(..) method
     * @throws Exception
     */
    @Test
    public void testIsDateSixteenYearsAgo() {
        String day = "14";
        String month = "7";
        String year = "1999";
        Assert.assertTrue(ValidationUtils.isDateSixteenYearsAgo(day, month, year));
        day = "15";
        month = "7";
        year = "2010";
        Assert.assertFalse(ValidationUtils.isDateSixteenYearsAgo(day, month, year));
        day = "15";
        month = "FeB";
        year = "1998";
        Assert.assertTrue(ValidationUtils.isDateSixteenYearsAgo(day, month, year));
        day = "15";
        month = "FeB";
        year = "1997";
        Assert.assertTrue(ValidationUtils.isDateSixteenYearsAgo(day, month, year));
        day = "15";
        month = "November";
        year = "1998";
        Assert.assertTrue(ValidationUtils.isDateSixteenYearsAgo(day, month, year));
        day = "15";
        month = "sept";
        year = "1998";
        Assert.assertTrue(ValidationUtils.isDateSixteenYearsAgo(day, month, year));

        day = "15";
        month = "November";
        year = "98";
        Assert.assertFalse(ValidationUtils.isDateSixteenYearsAgo(day, month, year));
    }

    /**
     * Tests the isEmailValid(..) method
     * @throws Exception
     */
    @Test
    public void testIsEmailValid() {
        String email = "sss@fff.com";
        Assert.assertTrue(ValidationUtils.isEmailValid(email));

        email = "me@kj.co.uk";
        Assert.assertTrue(ValidationUtils.isEmailValid(email));

        email = "me@";
        Assert.assertFalse(ValidationUtils.isEmailValid(email));

        email = "m@e@kj.co.uk";
        Assert.assertFalse(ValidationUtils.isEmailValid(email));

        email = "me@kj.co.uk";
        Assert.assertTrue(ValidationUtils.isEmailValid(email));

        email = "me@kjcom";
        Assert.assertFalse(ValidationUtils.isEmailValid(email));

        email = "mekj.co.uk";
        Assert.assertFalse(ValidationUtils.isEmailValid(email));

        email = "@kjcom";
        Assert.assertFalse(ValidationUtils.isEmailValid(email));

        email = "kjdd@nkln.b";
        Assert.assertTrue(ValidationUtils.isEmailValid(email));

        email = "what about spaces@iamanemail.com";
        Assert.assertFalse(ValidationUtils.isEmailValid(email));
    }
    /**
     * Tests the isDateSixWeeksAgoOrLess(..) method
     * @throws Exception
     */
    @Test
    public void testIsDateBetweenSixWeeksAgoAndToday() {
        
        Calendar cal = Calendar.getInstance();

        //Substract one day to current date.
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        
    	Date date =  cal.getTime();
   	
    	int dayNum   = cal.get(Calendar.DAY_OF_MONTH);
    	
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
    	
    	String day = String.valueOf(dayNum);
    	simpleDateFormat = new SimpleDateFormat("MMMM");
        String month = simpleDateFormat.format(date);
        simpleDateFormat = new SimpleDateFormat("YYYY");
        String year = simpleDateFormat.format(date);
       // Assert.assertTrue(ValidationHelper.isDateBetweenSixWeeksAgoAndToday(day, month, year));
        
        day = "30";
        month = "8";
        year = "2015";
        Assert.assertFalse(ValidationUtils.isDateBetweenSixWeeksAgoAndToday(day, month, year));
    	
    }
    
    /**
     * Tests the isDateSixWeeksAgoOrLess(..) method
     * @throws Exception
     */
    @Test
    public void testIsDateLaterThanToday() {
        
        Calendar calendar = Calendar.getInstance();
        
        calendar.add(Calendar.DATE, 10);
        
        Date futureDate = calendar.getTime();
        
        LocalDate localDate = futureDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        int dayNum   = localDate.getDayOfMonth();
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        
        String day = String.valueOf(dayNum);
        simpleDateFormat = new SimpleDateFormat("MMMM");
        String month = simpleDateFormat.format(futureDate);
        simpleDateFormat = new SimpleDateFormat("YYYY");
        String year = simpleDateFormat.format(futureDate);
        
        //Assert.assertTrue(ValidationHelper.isDateInTheFuture(day, month, year));
        
        day = "30";
        month = "8";
        year = "2015";
        Assert.assertFalse(ValidationUtils.isDateInTheFuture(day, month, year));
        
    }

    
    /**
     * Tests the method isStringLengthOver(..)
     */
    @Test
    public void testIsStringLengthOver() {
        
        String buffer = makeTestString(1001);
       
        Assert.assertTrue(ValidationUtils.isStringLengthOver(buffer, 1000));
        Assert.assertFalse(ValidationUtils.isStringLengthOver("string below 1000 chars", 1000));
        
        Assert.assertTrue(ValidationUtils.isStringLengthOver("asdcvbfgrthw", 10));
        Assert.assertFalse(ValidationUtils.isStringLengthOver("abcdefghyu", 10));
    }

    /**
     * Tests the method validateMultiFieldMaxLength(..) (when there are validation errors)
     * This is tested by simulating a situation in which there are four data fields and two of them
     * fail the validation (and the other two do not)
     */
    @Test
    public void testValidateMultiFieldMaxLengthErrors() {

        List<ValidationUtils.FieldInfo> fieldInfoToCheck = new ArrayList<>();

        String valueForReferenceLength = "Value 2 is not";
        int maxLength = valueForReferenceLength.length();
        String fieldId = "theFieldId";
        String code = "theCode";
        List<ValidationError> errors = new ArrayList<>();

        fieldInfoToCheck.add(new ValidationUtils.FieldInfo("Value 1 is too long",
                "Log message 1"));

        fieldInfoToCheck.add(new ValidationUtils.FieldInfo(valueForReferenceLength,
                "Log message 2"));

        fieldInfoToCheck.add(new ValidationUtils.FieldInfo("Value 3 is too long",
                "Log message 3"));

        fieldInfoToCheck.add(new ValidationUtils.FieldInfo("Value 4 is not",
                "Log message 4"));

        ValidationUtils.validateMultiFieldMaxLength(fieldInfoToCheck,
                maxLength, fieldId, code, errors);

        Assert.assertEquals(1, errors.size());
        Assert.assertEquals(fieldId, errors.get(0).getFieldId());
        Assert.assertEquals(code, errors.get(0).getErrorMessage());
    }

    /**
     * Tests the method validateMultiFieldMaxLength(..) (when there are no validation errors)
     * This is tested by simulating a situation in which there are four data fields and none of them
     * fail the validation
     */
    @Test
    public void testValidateMultiFieldMaxLengthNoErrors() {

        List<ValidationUtils.FieldInfo> fieldInfoToCheck = new ArrayList<>();

        String valueForReferenceLength = "Value 1 is not too long";
        int maxLength = valueForReferenceLength.length();
        String fieldId = "theFieldId";
        String code = "theCode";
        List<ValidationError> errors = new ArrayList<>();

        fieldInfoToCheck.add(new ValidationUtils.FieldInfo(valueForReferenceLength,
                "Log message 1"));

        fieldInfoToCheck.add(new ValidationUtils.FieldInfo("Nor is value 2",
                "Log message 2"));

        fieldInfoToCheck.add(new ValidationUtils.FieldInfo("Nor value 3",
                "Log message 3"));

        fieldInfoToCheck.add(new ValidationUtils.FieldInfo("Or 4",
                "Log message 4"));

        ValidationUtils.validateMultiFieldMaxLength(fieldInfoToCheck,
                maxLength, fieldId, code, errors);

        Assert.assertEquals(0, errors.size());
    }

    /**
     * Tests the method validateMultiFieldMaxLength(..) (when there are validation errors)
     * This is tested by simulating a situation in which there are four data fields and two of them
     * fail the validation (and the other two do not)
     */
    @Test
    public void testValidateFieldMaxLengthErrors() {

        String valueToCheck = "Value is too long";
        String logMessage = "My log message";
        int maxLength = valueToCheck.length() - 2;
        String fieldId = "theFieldId";
        String code = "theCode";
        List<ValidationError> errors = new ArrayList<>();

        ValidationUtils.validateFieldMaxLength(valueToCheck, maxLength, fieldId, code,
                logMessage, errors);

        Assert.assertEquals(1, errors.size());
        Assert.assertEquals(fieldId, errors.get(0).getFieldId());
        Assert.assertEquals(code, errors.get(0).getErrorMessage());
    }

    /**
     * Tests the method validateMultiFieldMaxLength(..) (when there are no validation errors)
     * This is tested by simulating a situation in which there are four data fields and none of them
     * fail the validation
     */
    @Test
    public void testValidateFieldMaxLengthNoErrors() {

        String valueToCheck = "Value 1 is not too long";
        String logMessage = "My log message";
        int maxLength = valueToCheck.length();
        String fieldId = "theFieldId";
        String code = "theCode";
        List<ValidationError> errors = new ArrayList<>();

        ValidationUtils.validateFieldMaxLength(valueToCheck, maxLength, fieldId, code,
                logMessage, errors);

        Assert.assertEquals(0, errors.size());
    }

    /**
     * Returns a (probably long) String with length of a specified number of characters
     *
     * @param noOfChars Specifies the number of characters that the resulting String should contain
     * @return A String with the number of characters that was specified
     */
    public static String makeTestString(int noOfChars) {

        StringBuilder buffer = new StringBuilder();
        for (int c=0 ; c< noOfChars ; c++){
            buffer.append('a');
        }
        
        return buffer.toString();
    }
    
    
    /**
     * This test checks if the Nino provided is of correct format
     *
     * 
     * 
     */
    @Test
    public void isNinoValidTest(){
    	String nino = "ab123456c";
    	String nino3 = "SG313344C";
    	
    	Assert.assertTrue(ValidationUtils.isValidNino(nino));

    	Assert.assertTrue(ValidationUtils.isValidNino(nino3));
    }
    
    /**
     * This test checks if the Nino provided is not of correct format
     *
     * 
     * 
     */
    @Test
    public void isNinoNotValidTest()
    {
    	String nino = "ab123456z";
    	String nino2 = "ab111111";
    	String nino3 = "SG313344E";
    	String nino4= "EEE12345A";
    	
    	Assert.assertFalse(ValidationUtils.isValidNino(nino));

    	Assert.assertFalse(ValidationUtils.isValidNino(nino2));
    	
    	Assert.assertFalse(ValidationUtils.isValidNino(nino3));
    	
    	Assert.assertFalse(ValidationUtils.isValidNino(nino4));
    }
    
    @Test
    public void isStringAlphaOnlyTest(){

    	List<String> testStrings = new ArrayList<String>();
    	testStrings.add("abc");
    	testStrings.add("abc abc");
    	testStrings.add("abc abc ");
    	testStrings.add("abc abc");
    	testStrings.add("abc d");
    	
    	
    	for(String str : testStrings){
    		Assert.assertTrue(ValidationUtils.isAlphaOnly(str));
    	}
    }
    
    @Test
    public void isStringNotAlphaTest(){

    	List<String> testStrings = new ArrayList<String>();
    	testStrings.add("abc1");
    	testStrings.add("abc@");
    	testStrings.add("1abc");
    	testStrings.add("a1b'c");
    	testStrings.add("");
    	testStrings.add(null);
    	 
    	for(String str : testStrings){
    		Assert.assertFalse(ValidationUtils.isAlphaOnly(str));
    	}
    }
    
	@Test
	public void isStringValidAddressTest() {

		List<String> testStrings = new ArrayList<String>();
		testStrings.add("abc");
		testStrings.add("abc abc");
		testStrings.add("123 456 ");
		testStrings.add("abc 123 ");
		testStrings.add("abc, 123");
		testStrings.add("abc'd");
		//testStrings.add("2\3 Duncan's Street"); // This one fails.

		for (String str : testStrings) {
			Assert.assertTrue(ValidationUtils.isValidAddress(str));
		}
	}

	@Test
	public void isStringNotValidAddressTest() {

		List<String> testStrings = new ArrayList<String>();
		testStrings.add("abc!");
		testStrings.add("ab1c@");
		testStrings.add("1abc?");
		testStrings.add("a123 bc+");
		testStrings.add("");
		testStrings.add(null);

		for (String str : testStrings) {
			Assert.assertFalse(ValidationUtils.isValidAddress(str));
		}
	}

	@Test
	public void isStringTelephoneOnlyTest() {
		List<String> testStrings = new ArrayList<String>();
		testStrings.add("01");
		testStrings.add("+44 0134567");
		testStrings.add("0115 2891759");
		testStrings.add("01152891759");

		for (String str : testStrings) {
			Assert.assertTrue(ValidationUtils.isTelephoneOnly(str));
		} 
	}

	@Test
	public void isStringNotTelephoneTest() {
		List<String> testStrings = new ArrayList<String>();
		testStrings.add("?01");
		testStrings.add("-44 0134567");
		testStrings.add("0!15 2891759");
		testStrings.add("Plus44 1152891759");
		testStrings.add("");
		testStrings.add(null);

		for (String str : testStrings) {
			Assert.assertFalse(ValidationUtils.isTelephoneOnly(str));
		}
	}
	
	@Test
	public void isAlphaNumericOnlyTest() {
		List<String> testStrings = new ArrayList<String>();
		testStrings.add("01");
		testStrings.add("A1B1");
		testStrings.add("CDE");
		testStrings.add("w3e4d5g");

		for (String str : testStrings) {
			Assert.assertTrue(ValidationUtils.isAlphaNumericOnly(str));
		} 
	}
	
	@Test
	public void isNotAlphaNumericOnlyTest() {
		List<String> testStrings = new ArrayList<String>();
		testStrings.add("01 01");
		testStrings.add("A1-B1");
		testStrings.add("CDE!");
		testStrings.add("w3 e4 d5 g");
		testStrings.add("1,2");
		testStrings.add("A1/B2");
		testStrings.add("");
		testStrings.add(null);

		for (String str : testStrings) {
			Assert.assertFalse(ValidationUtils.isAlphaNumericOnly(str));
		} 
	}
	
	@Test
	public void isValidNameTest() {
		List<String> testStrings = new ArrayList<String>();
		testStrings.add("Mr Jones");
		testStrings.add("Mr A.H. Smith");
		testStrings.add("Miss A Roger's");
		testStrings.add("A Jones-Green");
		testStrings.add("Mrs J,A White");
		

		for (String str : testStrings) {
			Assert.assertTrue(ValidationUtils.isValidName(str));
		} 
	}
	
	@Test
	public void isNotValidNameTest() {
		List<String> testStrings = new ArrayList<String>();
		testStrings.add("Mr Jones!");
		testStrings.add("Miss Smith the 1st");
		testStrings.add("Mr A/H Green");
		testStrings.add("123");
		testStrings.add("Mr & Mrs");
		testStrings.add("Mr Jones @");
		testStrings.add("");
		testStrings.add(null);

		for (String str : testStrings) {
			Assert.assertFalse(ValidationUtils.isValidName(str));
		} 
	}
	
	@Test
	public void isValidPostcode() {
		List<String> testStrings = new ArrayList<String>();
		testStrings.add("S10 3DP");
		testStrings.add("LS1 5QJ");
		testStrings.add("W1S 3QQ");
		testStrings.add("WS11 5DJ");
		testStrings.add("S1 1AB");
		

		for (String str : testStrings) {
			Assert.assertTrue(ValidationUtils.isValidPostcode(str));
		} 
	}
	
	@Test
	public void isNotValidPostcode() {
		List<String> testStrings = new ArrayList<String>();
		testStrings.add("S101 3DP");
		testStrings.add("LSS 5QJ");
		testStrings.add("W11S 3QQ");
		testStrings.add("WST1 5DJ");
		testStrings.add("S 1AB");
		testStrings.add("S1  1AB");
		testStrings.add("ABC");
		testStrings.add("123");
		testStrings.add("");
		testStrings.add(null);
		
		for (String str : testStrings) {
			Assert.assertFalse(ValidationUtils.isValidPostcode(str));
		} 
	}
	
}
