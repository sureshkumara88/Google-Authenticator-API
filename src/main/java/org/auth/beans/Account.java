package org.auth.beans;

import org.springframework.stereotype.Component;

/**
 * This class is act as BO for receiving request from the client
 * @author suresh kumar
 *
 */
@Component
public class Account {

	private String userName;
	
	private String otp;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
}
