package org.auth.util;

/**
 * This class represents as a constants definition
 * @author suresh kumar
 *
 */
public class Constants {
	
	// Controller
	public static final String REGISTER_ENDPOINT = "/auth/v1/2fa/ga/register";
	
	public static final String VALIDATE_ENDPOINT = "/auth/v1/2fa/ga/validate";
	
	public static final String REGISTER_FALLBACK = "fallbackRegister";
	
	public static final String VALIDATE_FALLBACK = "fallbackValidate";
	
	public static final String TIMEOUT_PROPERTY = "execution.isolation.thread.timeoutInMilliseconds";
	
	public static final String TIMEOUT_VALUE = "5000";
	
	public static final String THREADPOOL_CORE_SIZE_PROPERTY = "coreSize";
	
	public static final String THREADPOOL_MAX_SIZE_PROPERTY = "maxQueueSize";
	
	public static final String THREADPOOL_CORE_SIZE_VALUE = "4";
	
	public static final String THREADPOOL_MAX_SIZE_VALUE = "10";
	
	public static final String SUPPRESS_WARNING = "unused";
	
	// Service
	public static final int TOTP_LENGTH = 6;
	
	public static final String VALIDATE_SUCCESS = "Validation successful";
	
	public static final String VALIDATE_FAILURE = "Validation not successful";
	
	// Error message 
	public static final String USERNAME_ERROR_MSG_1 = "userName is not available in request.";
	
	public static final String USERNAME_ERROR_MSG_2 = "userName is not available in our repository, please register first.";
	
	public static final String USERNAME_OTP_ERROR_MSG_1 = "userName and otp are not available in request.";
	
	public static final String USERNAME_OTP_ERROR_MSG_2 = "userName is not available and otp must be 6 digits in request.";
	
	public static final String OTP_ERROR_MSG_1 = "otp is not available in request.";
	
	public static final String OTP_ERROR_MSG_2 = "otp must be 6 digits in request.";
	
	public static final String FALLBACK_REGISTER_MSG = "Register request fails, It takes long time to respond. Please try again.";
	
	public static final String FALLBACK_VALIDATE_MSG = "Validate request fails, It takes long time to respond. Please try again.";
	
}
