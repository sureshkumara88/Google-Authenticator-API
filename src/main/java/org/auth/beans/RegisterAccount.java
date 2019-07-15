package org.auth.beans;

import org.springframework.stereotype.Component;

/**
 * This class is act as BO for sending response to the client 
 * @author suresh kumar
 *
 */
@Component
public class RegisterAccount {

	private String secretKey;
	
	private String errorMessage;

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
