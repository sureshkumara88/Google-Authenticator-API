package org.auth.service;

import static org.auth.repository.CredentialRepository.getAuthenticatorApi;

import org.auth.repository.CredentialRepository;
import org.auth.util.Constants;
import org.springframework.stereotype.Service;

import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

/**
 * This class act as a service to register the userName with generated secret key
 * @author suresh kumar
 *
 */
@Service
public class RegisterServiceImpl implements RegisterService {

	@Override
	public String register(String userName) throws Exception {		
		String secretKey = "";

		validateRequest(userName);
		
		GoogleAuthenticatorKey key = getAuthenticatorApi().createCredentials();
		CredentialRepository.getInstance().saveUserCredentials(userName, key.getKey(), key.getVerificationCode(), key.getScratchCodes());
		getAuthenticatorApi().authorize(key.getKey(), key.getVerificationCode(), 30);
		secretKey = CredentialRepository.getInstance().getSecretKey(userName);

		return secretKey;
	}

	private void validateRequest(String userName) throws Exception {
		
		if (userName == null || userName.length() == 0) {
			throw new Exception(Constants.USERNAME_ERROR_MSG_1);
		}
	}
	
}
