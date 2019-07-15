package org.auth.beans;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * This class is act as BO to store the account and secret key details
 * @author suresh kumar
 *
 */
@Component
public class Authenticator implements Comparable<Authenticator> {

	private String userName;
	
	private String secretKey;
	
	private int validationCode;
	
	private List<Integer> scratchCodes;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public int getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(int validationCode) {
		this.validationCode = validationCode;
	}

	public List<Integer> getScratchCodes() {
		return scratchCodes;
	}

	public void setScratchCodes(List<Integer> scratchCodes) {
		this.scratchCodes = scratchCodes;
	}

	@Override
	public int compareTo(Authenticator obj) {
		return this.userName.compareTo(obj.getUserName());
	}
		
}
