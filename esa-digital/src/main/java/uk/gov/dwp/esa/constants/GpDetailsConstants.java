package uk.gov.dwp.esa.constants;

public enum GpDetailsConstants {
	
	DOCTOR_NAME("doctorName"),
	DOCTOR_ADDRESS_LINE1("docAddLine1"),
	DOCTOR_ADDRESS_LINE2("docAddLine2"),
	DOCTOR_ADDRESS_LINE3("docAddLine3"),
	DOCTOR_ADDRESS_LINE4("docAddLine4"),
	DOCTOR_POSTCODE("docPostCode"),
	DOCTOR_TEL_NUMBER("docTelNumber");
	
	private String value;
	
	GpDetailsConstants(String value){
		this.value=value;
	}
	
	public String value(){
		return value;
	}
	
}

