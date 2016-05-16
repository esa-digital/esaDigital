/**
 * © Crown copyright 2015. This source code is licensed under the <a
 * href="https://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/"
 * rel="license">Open Government Licence 3.0</a>. You may use and re-use the code free of charge in
 * any format or medium, under the terms of the Open Government Licence. When you use this code, you
 * should include the following attribution: "[fully qualified class name|full package name], Crown
 * copyright, 2015, licensed under the <a
 * href="https://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/">Open Government
 * Licence 3.0</a>". Any email enquiries regarding the use and re-use of this information resource
 * should be sent to: psi@nationalarchives.gsi.gov.uk.
 */

package uk.gov.dwp.esa.validatorHelpers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Helper class with methods used to aid validation 
 *
 * @author ESA
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ValidationUtils {

    private static final String DATE_SEPERATOR = "/";
    private static final String DIGITS_4_PATTERN = "\\d{4}";
    private static final String DIGITS_1_TO_2_PATTERN = "\\d{1,2}";
    private static final String SEPT_PATTERN = "(?i)sept";
    private static final String MONTHS_LIST_SHORT_PATTERN = "(?i)jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec";
    private static final String MONTHS_LIST_PATTERN = "(?i)january|february|march|april|may|june|july|august|september|october|november|december";
    private static final String DATE_FORMAT_1 = "dd/MM/yyyy";
    private static final String DATE_FORMAT_2 = "dd/MMM/yyyy";
    public static final int MAX_FIELD_CLOSED_QUESTION = 1000;
    public static final int MAX_FIELD_OPEN_QUESTION = 10000;
    public static final String NINO_PATTERN="^(?!BG|GB|NK|KN|TN|NT|ZZ)[ABCEGHJ-PRSTW-Z][ABCEGHJ-NPRSTW-Z]\\d{6}[A-D]$";
    public static final String ALPHA_ONLY_PATTERN ="^[a-zA-Z\\s-',]*$";

    public static final String TOP_OF_PAGE_ERRORS_KEY = "topOfPageErrors";

    // Give this class its logging vehicle
    private static final Logger logger = LogManager.getLogger(ValidationUtils.class);

    /**
     * This class is a collection of static methods. This private 
     * constructor ensures that it is not instantiated by mistake 
     */
    private ValidationUtils() {
    }

    /**
     * Replaces all instances of angle brackets in the text with the html entities. This is so they
     * are rendered correctly when displayed as html
     * 
     * @param text
     * @return the String text with square brackets replaced
     */
    public static String replaceAngleBracketsWithHtmlEntity(String text) {
        if (text == null){
            return null;
        }
        String textToReturn = text.replaceAll("<", "&lt;");
        textToReturn = textToReturn.replaceAll(">", "&gt;");
        return textToReturn;
    }

    /**
     * Checks if the string is null or empty
     * 
     * @param string
     * @return true if string is null or empty
     */
    public static boolean isEmpty(String string) {
        if (string == null || "".equals(string)){
            return true;
        }
        return false;
    }
    
    /**
     * Checks if the string length is greater than maxLength
     * 
     * @param string maxLength
     * @return true if string is greater than maxLength
     */
    public static boolean isStringLengthOver(String string, int maxLength) {
        if (string==null){
            return false;
        }
        
        if (string.length() > maxLength){
            return true;
        }
        return false;
    }
    
    /**
     * Returns true if the string dateToValidate is a valid date
     * 
     * @param dateToValidate
     * @return true or false
     */
    private static boolean isValidCalenderDate(String dateToValidate) {

        if (null == dateToValidate) {
            return false;
        }
        // Validate the date against the types of pattern that are allowed
        return isDateValidInFormat(DATE_FORMAT_1, dateToValidate)
                || isDateValidInFormat(DATE_FORMAT_2, dateToValidate);
    }

    /**
     * Check if the date is in the provided format 
     * @param format
     * @param dateToValidate
     * @return a boolean to indicate if the date is in the provided format
     */
    private static boolean isDateValidInFormat(String format, String dateToValidate) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            sdf.parse(dateToValidate);
            return true;
        } catch (java.text.ParseException e) {
            return false;
        }
    }

    /**
     * Returns true if the three string parameters make up a valid date
     * 
     * day may be entered as ‘dd’ or ‘d’ (e.g. 08 or 8) month may be entered as ‘mm’ or ‘m’ (e.g. 04
     * or 4) year must be entered as ‘yyyy’ (e.g. 2014)
     * 
     * must contain a valid date.
     * 
     * @param day
     * @param month
     * @param year
     * @return true or false
     */
    public static boolean isDateValid(String day, String month, String year) {
        if (ValidationUtils.isEmpty(day) || ValidationUtils.isEmpty(month)
                || ValidationUtils.isEmpty(year)) {
            return false;
        }

        boolean isDayValid = Pattern.matches(DIGITS_1_TO_2_PATTERN, day);

        boolean isMonthValid = Pattern.matches(DIGITS_1_TO_2_PATTERN, month);
        if (!isMonthValid) {
            isMonthValid = Pattern.matches(MONTHS_LIST_SHORT_PATTERN, month);
        }
        if (!isMonthValid) {
            isMonthValid = Pattern.matches(MONTHS_LIST_PATTERN, month);
        }
        if (!isMonthValid) {
            isMonthValid = Pattern.matches(SEPT_PATTERN, month);
            if (isMonthValid) {
                month = "sep";// This is changed to "sep" here so that the next
                              // step which check the date validity will pass
                              // validation as "sept" is not recognised
            }
        }
        //We need to check if the date is pre 1900
        
        boolean isYearValid = Pattern.matches(DIGITS_4_PATTERN, year);
        if (isYearValid && Integer.parseInt(year)<1900) {
        	isYearValid=false;
        }
        

        if (!isDayValid || !isMonthValid || !isYearValid) {
            return false;
        }
        return isValidCalenderDate(day + DATE_SEPERATOR + month + DATE_SEPERATOR + year);
    }

    /**
     * First checks if the 3 string parameters are a valid date and then checks if it is sixteen
     * years ago or more. If both conditions are satisfied then returns true.
     * 
     * @param day
     * @param month
     * @param year
     * @return true or false
     */
    public static boolean isDateSixteenYearsAgo(String day, String month, String year) {

        if (!isDateValid(day, month, year)){
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_1);
        if (month.length() > 2 && year.length() == 4) {
            sdf = new SimpleDateFormat(DATE_FORMAT_2);
        }
        sdf.setLenient(false);

        try {
            // if not valid, it will throw ParseException
            if ("sept".equalsIgnoreCase(month)) {
                month = "sep"; // This is changed to "sep" here so that the next
                               // step which check the date validity will pass
                               // validation as "sept" is not recognised
            }
            Date date = sdf.parse(day + DATE_SEPERATOR + month + DATE_SEPERATOR + year);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -16);
            Date dateSisteenYearsAgo = calendar.getTime();

            if (dateSisteenYearsAgo.before(date)){
                return false;
            }
        } catch (java.text.ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * Validates the email
     * @param email
     * @return true if email is a valid email address format, false otherwise
     */
    public static boolean isEmailValid(String email) {
        if (isEmpty(email)){
            return false;
        }
        if (!email.contains("@")){
            return false;
        }
        
        if (email.contains(" ")) {
            return false;
        }

        // Cannot contain more than one @ sign
        String emailAfterTheAtSign = email.substring(email.indexOf('@') + 1);
        if (emailAfterTheAtSign.contains("@")){
            return false;
        }
        String afterTheAtSign = email.substring(email.lastIndexOf('@'));
        if (!afterTheAtSign.contains(".")){
            return false;
        }
        return true;
    }
    /**
     * Check that the date is between six weeks in the past and today's date.
     * 
     * 
     * @param day
     * @param month
     * @param year
     * @return true or false
     */
    public static boolean isDateBetweenSixWeeksAgoAndToday(String day, String month, String year) {
    	
    	if (!isDateValid(day, month, year)){
            return false;
        }

    	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_1);
        if (month.length() > 2 && year.length() == 4) {
            sdf = new SimpleDateFormat(DATE_FORMAT_2);
        }
        sdf.setLenient(false);

        try {
            // if not valid, it will throw ParseException
            if ("sept".equalsIgnoreCase(month)) {
                month = "sep"; 
            }
                 
            Date date = new Date();
            Date startDate = sdf.parse(day + DATE_SEPERATOR + month + DATE_SEPERATOR + year);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.WEEK_OF_YEAR, -6);
            Date dateSixWeeksAgo = calendar.getTime();
            
            if (startDate.before(date)
					&& startDate.after(dateSixWeeksAgo)) {
                return true;
            }
        } catch (java.text.ParseException e) {
            return false;
        }
    	return false;
    }
    /**
     * Check that the date is in the future.
     * 
     * 
     * @param day
     * @param month
     * @param year
     * @return true or false
     */
    public static boolean isDateInTheFuture(String day, String month, String year) {
    	
    	if (!isDateValid(day, month, year)){
            return false;
        }
    	
    	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_1);
        if (month.length() > 2 && year.length() == 4) {
            sdf = new SimpleDateFormat(DATE_FORMAT_2);
        }
        try {
            // if not valid, it will throw ParseException
            if ("sept".equalsIgnoreCase(month)) {
                month = "sep"; 
            }
                 
            Date date = new Date();
            Date startDate = sdf.parse(day + DATE_SEPERATOR + month + DATE_SEPERATOR + year);
           
            if (startDate.after(date)) {
                return true;
            }
            
            
        } catch (java.text.ParseException e) {
            return false;
        }
    	

    	return false;
    }

    /**
     * This convenience method brings together the logic for validating a text field's maximum
     * length and reporting and logging the error if it exceeds the max length. It saves repeating
     * the same boilerplate logic multiple times in the validators.
     *
     * @param fieldValueToCheck This is the String field value to check, which could be null
     * @param maxLength This specifies the maximum allowed length to validate against
     * @param fieldIdentifier Identifies the field for the validation message on failed validation
     * @param validationCode Identifies the validation error code to report (by adding it to)
     * the errors list) on failed validation
     * @param logMessage Provides a log message to log on failed validation
     * @param errors The list of validation errors to which any detected here are to be added
     */
    public static void validateFieldMaxLength(String fieldValueToCheck,
            int maxLength,
            String fieldIdentifier,
            String validationCode,
            String logMessage,
            List<ValidationError> errors) {

        //Setup a list of just one FieldInfo for the value to check and log message
        List<FieldInfo> fieldInfoToCheck = new ArrayList<>();
        fieldInfoToCheck.add(new FieldInfo(fieldValueToCheck, logMessage));

        //Now delegate the call to the multiple field version, giving it just the one field
        validateMultiFieldMaxLength(fieldInfoToCheck,
                maxLength,
                fieldIdentifier,
                validationCode,
                errors);
    }

    /**
     * This convenience method brings together the logic for validating the maximum lengths
     * of a set of test fields that are treated as a group for validation and reporting (against
     * a single field for all of them) and logging the error if any of them exceeds the maximum
     * length. This is applicable to groups of address lines, where each line has the same
     * maximum length but any errors are displayed against the address as a whole. This method
     * saves repeating the same boilerplate logic multiple times across the validators. It makes
     * use of the FieldInfo class that is nested within this class.
     *
     * @param fieldInfoToCheck
     * @param maxLength
     * @param fieldIdentifier
     * @param validationCode
     * @param errors
     */
    public static void validateMultiFieldMaxLength(List<FieldInfo> fieldInfoToCheck,
            int maxLength,
            String fieldIdentifier,
            String validationCode,
            List<ValidationError> errors) {

        // Fail if we've been called with an empty list as that's a logic error
        if (fieldInfoToCheck.size() < 1) {
            throw new IllegalArgumentException(
                    "No field information to check has been provided (empty list)");
        }

        // Now do the validation field by field and stop if we find a validation error
        for (FieldInfo fieldInfo : fieldInfoToCheck) {
            String fieldValueToCheck = fieldInfo.getFieldValueToCheck();
            String logMessage = fieldInfo.getLogMessage();

            if (fieldValueToCheck != null
                    && ValidationUtils.isStringLengthOver(fieldValueToCheck, maxLength)) {

                logger.error(logMessage);
                errors.add(new ValidationError(fieldIdentifier, validationCode));
                break; //If we've found the validation error we don't need to check any further
            }
        }
    }
    
    /**
     * This method will check if the nino passes is in right format
     *
     * @param nino
     */
    public static boolean isValidNino(String nino) {
    	
    		nino = nino.toUpperCase();
    		return Pattern.matches(NINO_PATTERN,nino);
	}
    
    /**
     * This method will check if the value passed does not contain numbers
     *
     * @param any String
     */
    public static boolean isAlphaOnly(String value) {
    	
    	if (value!=null && !value.equals("") ) {
    		return Pattern.matches(ALPHA_ONLY_PATTERN,value);
    	} 
    		return false;
    		
	}
    

    /**
     * Class to represent field-specific data for a field that is to be validate among a group.
     * The class encapsulates the value to be validated and the message to log if that value
     * fails the validation. Other attributes are not modeled, as the treatment of a group of
     * fields as a single unit of validation assumes that every other aspect is common across the
     * fields.
     */
    public static class FieldInfo {

        private final String fieldValueToCheck;
        private final String logMessage;

        /**
         *
         * @param fieldValueToCheck the field value to validate for this field
         * @param logMessage the message to log if this field's value fails validation
         */
        public FieldInfo(String fieldValueToCheck, String logMessage) {
            this.fieldValueToCheck = fieldValueToCheck;
            this.logMessage = logMessage;
        }

        /**
         * Get the FieldValueToCheck for this field
         *
         * @return
         */
        public String getFieldValueToCheck() {
            return fieldValueToCheck;
        }

        /**
         * Get the logMessage to log if this field's value fails validation
         *
         * @return
         */
        public String getLogMessage() {
            return logMessage;
        }
    }
}
