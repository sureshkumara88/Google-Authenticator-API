package org.auth.repository;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.auth.util.Utility.padNumber;

import org.auth.beans.Authenticator;
import org.auth.util.Constants;
import org.springframework.stereotype.Repository;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.ICredentialRepository;

/**
 * This class act as repository to store and retrieve the list of accounts with secret key and generate totp
 * @author suresh kumar
 *
 */
@Repository
public class CredentialRepository implements ICredentialRepository {
	
	private Set<Authenticator> authRecords;
	
	private static CredentialRepository instance;

	private static GoogleAuthenticator authenticatorApi = new GoogleAuthenticator();

	private CredentialRepository() {
		authRecords = new TreeSet<Authenticator>();
	}

	public static CredentialRepository getInstance() {
		if(instance == null) {
			instance = new CredentialRepository();
		}
		return instance;
	}

	/**
	 * This method is used to return the authenticator object for given userName
	 * @param userName
	 * @return
	 */
	public Authenticator getUser(String userName) {
		
		return authRecords.stream().filter(record -> record.getUserName().equals(userName)).findAny().orElse(null);
	}

	/**
	 * This method is used to generate and return the dynamic TOTP for given userName
	 * @param userName
	 * @return
	 */
	public String getTotp(String userName) {
		String totp = "";

		String secretKey = getSecretKey(userName);
		
		if (secretKey.length() > 0) {
			totp = padNumber(new Integer(getAuthenticatorApi().getTotpPassword(secretKey)).toString(), Constants.TOTP_LENGTH);
		}

		return totp;
	}
	
	/**
	 * This method is used to return the secret key for given userName
	 * @param userName
	 * @return
	 */
	@Override
	public String getSecretKey(String userName) {
		String secretKey = "";

		Authenticator auth = getUser(userName);
		if (auth != null) {
			secretKey = auth.getSecretKey();
		}  

		return secretKey;
	}

	/**
	 * This method is used to save the authenticator details and add it into collection
	 * @param userName, secretKey, validationCode and scratchCodes
	 */
	@Override
	public void saveUserCredentials(String userName, String secretKey, int validationCode, List<Integer> scratchCodes) {
		Authenticator auth = new Authenticator();
		auth.setUserName(userName);
		auth.setSecretKey(secretKey);
		auth.setValidationCode(validationCode);
		auth.setScratchCodes(scratchCodes);
		authRecords.add(auth);
	}

	public static GoogleAuthenticator getAuthenticatorApi() {
		return authenticatorApi;
	}

}
