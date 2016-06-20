package uk.gov.dwp.esa.constants;

public enum BankDetailsConstants {
	
	ACCOUNTHOLDER("acctHolderName"),
	SORTCODE("sortCode"),
	ACCOUNTNUMBER("accountNumber"),
	BUILDINGSOCIETY("buildingSociety");
	
	private String value;
	
	BankDetailsConstants(String value){
		this.value = value;
	}

	public String value(){
		return value;
	}
}
