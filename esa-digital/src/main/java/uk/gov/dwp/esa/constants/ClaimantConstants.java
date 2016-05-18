package uk.gov.dwp.esa.constants;


public enum ClaimantConstants {
	
	TITLE("title"),
	FIRST_NAME("firstName"),
	SURNAME("surname"),
	OTHERNAME("otherName"),
	DOB("dobYear"),
	GENDER("gender"),
	NINO("nino");
	
	private String value;
	
	ClaimantConstants(String value){
		this.value=value;
	}
	
	public String value(){
		return value;
	}
	
}


