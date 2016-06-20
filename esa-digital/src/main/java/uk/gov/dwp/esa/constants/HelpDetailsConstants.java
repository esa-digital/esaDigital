package uk.gov.dwp.esa.constants;

public enum HelpDetailsConstants {

	
	HELPPERSONNAME("personName"),
	HELPPERSONRELATION("personRelation"),
	REASONFORHELP("reasonForHelp"),
	TITLE("title"),
	FIRST_NAME("firstName"),
	SURNAME("surname"),
	OTHERNAME("otherName"),
	DOB("dobYear"),
	GENDER("gender"),
	NINO("nino"),
	ADDRESS_LINE1("addLine1"),
	ADDRESS_LINE2("addLine2"),
	ADDRESS_LINE3("addLine3"),
	ADDRESS_LINE4("addLine4"),
	POSTCODE("postCode"),
	TEL_NUMBER("telNumber");
	
	private String value;
	
	HelpDetailsConstants(String value){
		this.value = value;
	}

	public String value(){
		return value;
	}
}
