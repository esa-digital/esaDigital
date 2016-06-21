package uk.gov.dwp.esa.constants;

public enum AlternateFormatsConstants {
	
	ALTERNATE_FORMAT("alternativeFormatYN"),
	ALTERNATE_FORMAT_TYPE("alternateFormatType"),
	ALTERNATE_FORMAT_OTHER("alternateFormatTypeOther");
	
	
	private String value;
	
	AlternateFormatsConstants(String value){
		this.value=value;
	}
	
	public String value(){
		return value;
	}
	
}

