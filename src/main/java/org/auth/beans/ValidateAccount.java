package org.auth.beans;

import org.springframework.stereotype.Component;

/**
 * This class is act as BO for sending response to the client
 * @author suresh kumar
 *
 */
@Component
public class ValidateAccount {

	private String status;
	
	private String errorMessage;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
