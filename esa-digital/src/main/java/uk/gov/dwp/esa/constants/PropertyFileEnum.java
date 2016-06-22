package uk.gov.dwp.esa.constants;

public enum PropertyFileEnum {

	CLAIMANT_PROPERTY("C:\\GitRepos\\esaDigital\\esa-digital\\src\\main\\resources\\messages.properties");
	
private String value;
	
	PropertyFileEnum(String value){
		this.value=value;
	}
	
	public String value(){
		return value;
	}
}
