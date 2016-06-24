package uk.gov.dwp.esa.constants;

public enum PropertyFileEnum {

	GENERIC_CONTENT("content"),
	CLAIMANT_PROPERTY("personal-details.properties"),
	CONTACT_DETAILS_PROPERTY("contact-details.properties");
	
private String value;
	
	PropertyFileEnum(String value){
		this.value=value;
	}
	
	public String value(){
		return value;
	}
}
