package org.auth.service;

public interface ValidateService {

	public String validateUser(String userName, String otp) throws Exception;
	
}
