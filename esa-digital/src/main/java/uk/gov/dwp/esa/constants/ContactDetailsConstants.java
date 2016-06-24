package uk.gov.dwp.esa.constants;


public enum ContactDetailsConstants {
	
	ADDRESSLINE1("addressLine1"),
	ADDRESSLINE2("addressLine2"),
	ADDRESSLINE3("addressLine3"),
	ADDRESSLINE4("addressLine4"),
	POSTCODE("postCode"),
	PHONENUMBER("phoneNumber"),
	OTHERNUMBER("otherNumber"),
	EMAIL("email");
	
	private String value;
	
	ContactDetailsConstants(String value){
		this.value=value;
	}
	
	public String value(){
		return value;
	}
	
}


