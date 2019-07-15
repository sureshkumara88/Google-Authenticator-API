package org.auth.controller;


import org.auth.beans.Account;
import org.auth.beans.RegisterAccount;
import org.auth.beans.ValidateAccount;
import org.auth.service.RegisterService;
import org.auth.service.RegisterServiceImpl;
import org.auth.service.ValidateService;
import org.auth.service.ValidateServiceImpl;
import org.auth.util.Constants;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * This class act as a controller to receive the request, process it and send back the response to client
 * @author suresh kumar
 *
 */
@RestController
public class AuthenticationController {

	/**
	 * This method is used to register the given account with generated secret key
	 * @param account
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping(method = RequestMethod.POST, value= Constants.REGISTER_ENDPOINT)
	@HystrixCommand(fallbackMethod = Constants.REGISTER_FALLBACK, 
					commandProperties = {@HystrixProperty(name = Constants.TIMEOUT_PROPERTY, value = Constants.TIMEOUT_VALUE)},
					threadPoolProperties = {@HystrixProperty(name = Constants.THREADPOOL_CORE_SIZE_PROPERTY, value = Constants.THREADPOOL_CORE_SIZE_VALUE), 
											@HystrixProperty(name = Constants.THREADPOOL_MAX_SIZE_PROPERTY, value = Constants.THREADPOOL_MAX_SIZE_VALUE)})
	public RegisterAccount register(@RequestBody Account account) {
		RegisterAccount response = new RegisterAccount();
		RegisterService registerService = new RegisterServiceImpl();
		
		try {
			String secretKey = registerService.register(account.getUserName());
			response.setSecretKey(secretKey);
		} catch (Exception ex) {
			response.setErrorMessage(ex.getMessage());
		}

		return response;
	}

	/**
	 * This method is used to validate the given account and otp against the Google Authenticator API
	 * @param account
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value= Constants.VALIDATE_ENDPOINT)
	@HystrixCommand(fallbackMethod = Constants.VALIDATE_FALLBACK, 
					commandProperties = {@HystrixProperty(name = Constants.TIMEOUT_PROPERTY, value = Constants.TIMEOUT_VALUE)},
					threadPoolProperties = {@HystrixProperty(name = Constants.THREADPOOL_CORE_SIZE_PROPERTY, value = Constants.THREADPOOL_CORE_SIZE_VALUE), 
											@HystrixProperty(name = Constants.THREADPOOL_MAX_SIZE_PROPERTY, value = Constants.THREADPOOL_MAX_SIZE_VALUE)})
	public ValidateAccount validate(@RequestBody Account account) {
		ValidateAccount response = new ValidateAccount();
		ValidateService validateService = new ValidateServiceImpl();
		
		try {
			String result = validateService.validateUser(account.getUserName(), account.getOtp());
			response.setStatus(result);
		} catch (Exception ex) {
			response.setErrorMessage(ex.getMessage());
		}

		return response;
	}
	
	
	@SuppressWarnings(Constants.SUPPRESS_WARNING)
	private RegisterAccount fallbackRegister(Account account) {
		RegisterAccount response = new RegisterAccount();
		response.setErrorMessage(Constants.FALLBACK_REGISTER_MSG);
		return response;
	}

	@SuppressWarnings(Constants.SUPPRESS_WARNING)
	private ValidateAccount fallbackValidate(Account account) {
		ValidateAccount response = new ValidateAccount();
		response.setErrorMessage(Constants.FALLBACK_VALIDATE_MSG);
		return response;
	}
	
}
