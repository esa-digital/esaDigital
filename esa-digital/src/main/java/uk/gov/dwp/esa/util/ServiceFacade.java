package uk.gov.dwp.esa.util;

import org.springframework.stereotype.Component;

public class ServiceFacade {
	
	public static void main(String str[]){
		String propertyHome = System.getenv("JAV_HOME");
		System.out.println("JAVA HOME IS" + propertyHome);
	}
	
	private String endpoint;

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
	
}
