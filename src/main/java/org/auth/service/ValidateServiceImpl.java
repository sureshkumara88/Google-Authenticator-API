package org.auth.service;

import org.auth.repository.CredentialRepository;
import org.auth.util.Constants;
import org.springframework.stereotype.Service;

/**
 * This class act as a service to validate the given userName and otp against the Google Authenticator API
 * @author suresh kumar
 *
 */
@Service
public class ValidateServiceImpl implements ValidateService {

	@Override
	public String validateUser(String userName, String otp) throws Exception {
		String result = "";
		
		validateRequest(userName, otp);

		String totp = CredentialRepository.getInstance().getTotp(userName);

		if (totp.equals(otp)) {
			result = Constants.VALIDATE_SUCCESS;
		} else {
			result = Constants.VALIDATE_FAILURE;
		}

		return result;
	}

	private void validateRequest(String userName, String otp) throws Exception {

		if ((userName == null  || userName.length() == 0) && (otp == null  || otp.length() == 0)) {
			throw new Exception(Constants.USERNAME_OTP_ERROR_MSG_1);
		} else if ((userName == null  || userName.length() == 0) && (otp != null  && otp.length() != Constants.TOTP_LENGTH)) {
			throw new Exception(Constants.USERNAME_OTP_ERROR_MSG_2);
		} else if (userName == null  || userName.length() == 0) {
			throw new Exception(Constants.USERNAME_ERROR_MSG_1);
		} else if (otp == null  || otp.length() == 0) {
			throw new Exception(Constants.OTP_ERROR_MSG_1);
		} else if (otp.length() != 6) {
			throw new Exception(Constants.OTP_ERROR_MSG_2);
		} else if (CredentialRepository.getInstance().getUser(userName) == null) {
			throw new Exception(Constants.USERNAME_ERROR_MSG_2);
		}
	}

}
