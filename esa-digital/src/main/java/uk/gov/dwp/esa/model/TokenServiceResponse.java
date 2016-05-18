package uk.gov.dwp.esa.model;

public class TokenServiceResponse{

	/**
	 * not a serializable object
	 */
	
	private String customerId;
	private String inviteKey;
	private String status;
	
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getInviteKey() {
		return inviteKey;
	}
	public void setInviteKey(String inviteKey) {
		this.inviteKey = inviteKey;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
