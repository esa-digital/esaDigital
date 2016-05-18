package uk.gov.dwp.esa.model;

public class TokenServiceRequest {
	
	/**
	 * not serializable object
	 */
	
	private String customerId;
	private String surname;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

}
