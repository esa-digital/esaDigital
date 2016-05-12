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

/**
 * Represents a validation error on a field. Contains the field ID and a message code
 * 
 * @author ESA
 */
public class ValidationError {

    private String fieldId;
    private String messageCode;

    /**
     * Constructor
     * @param fieldId
     * @param messageCode
     */
    public ValidationError(String fieldId, String messageCode) {
        super();
        this.fieldId = fieldId;
        this.messageCode = messageCode;
    }

    /**
     * @return the fieldId
     */
    public String getFieldId() {
        return fieldId;
    }

    /**
     * @param fieldId the fieldId to set
     */
    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * @return the messageCode
     */
    public String getMessageCode() {
        return messageCode;
    }

    /**
     * @param messageCode the messageCode to set
     */
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

}
