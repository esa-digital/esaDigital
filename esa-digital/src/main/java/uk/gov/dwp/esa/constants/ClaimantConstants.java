package uk.gov.dwp.esa.constants;


public enum ClaimantConstants {
	
	TITLE("claimant.title"),
	FIRST_NAME("claimant.firstName"),
	SURNAME("claimant.surname"),
	OTHERNAME("claimant.otherName"),
	DOB("claimant.DOB"),
	GENDER("claimant.gender"),
	NINO("claimant.nino");
	
	private String value;
	
	ClaimantConstants(String value){
		this.value=value;
	}
	
	public String value(){
		return value;
	}
	
}


