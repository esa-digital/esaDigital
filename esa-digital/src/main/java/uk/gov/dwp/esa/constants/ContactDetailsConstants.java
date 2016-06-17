package uk.gov.dwp.esa.constants;


public enum ContactDetailsConstants {
	
	ADDRESSLINE1("addressline1"),
	ADDRESSLINE2("addressline2"),
	ADDRESSLINE3("addressline3"),
	ADDRESSLINE4("addressline4"),
	POSTCODE("postcode"),
	PHONENUMBER("phonenumber"),
	OTHERNUMBER("othernumber"),
	EMAIL("email");
	
	private String value;
	
	ContactDetailsConstants(String value){
		this.value=value;
	}
	
	public String value(){
		return value;
	}
	
}


