package uk.gov.dwp.esa.constants;

public enum PropertyFileEnum {

	CLAIMANT_PROPERTY("personal-details.properties");
	
private String value;
	
	PropertyFileEnum(String value){
		this.value=value;
	}
	
	public String value(){
		return value;
	}
}
