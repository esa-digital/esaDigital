package uk.gov.dwp.esa.constants;

public enum HelpDetailsConstants {

	HELPDETAILSTYPE("helpDetailsType"),
	HELPPERSONNAME("thirdPartyDetails.personName"),
	HELPPERSONRELATION("thirdPartyDetails.personRelation"),
	REASONFORHELP("thirdPartyDetails.reasonForHelp"),
	TITLE("thirdPartyDetails.title"),
	FIRST_NAME("thirdPartyDetails.firstName"),
	SURNAME("thirdPartyDetails.surname"),
	APPOINTEE_FIRST_NAME("thirdPartyDetails.appointeeFirstName"),
	APPOINTEE_SURNAME("thirdPartyDetails.appointeeSurname"),
	OTHERNAME("thirdPartyDetails.otherName"),
	DOB("thirdPartyDetails.dobYear"),
	GENDER("thirdPartyDetails.gender"),
	NINO("thirdPartyDetails.nino"),
	ADDRESS_LINE1("thirdPartyDetails.addLine1"),
	ADDRESS_LINE2("thirdPartyDetails.addLine2"),
	ADDRESS_LINE3("thirdPartyDetails.addLine3"),
	ADDRESS_LINE4("thirdPartyDetails.addLine4"),
	POSTCODE("thirdPartyDetails.postCode"),
	TEL_NUMBER("thirdPartyDetails.telNumber"),
	BEHALF_Of_TYPE("thirdPartyDetails.behalfOfType"),
	SELF_TYPE("self"),
	THIRD_PARTY_HELP_TYPE("thirdPartyHelp"),
	BEHALF_TYPE("behalfType"),
	
	// TODO: remove hardcoded value
	APPOINTEE_TYPE("appointeeCheck");
	
	private String value;
	
	HelpDetailsConstants(String value){
		this.value = value;
	}

	public String value(){
		return value;
	}
}
