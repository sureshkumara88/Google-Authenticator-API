package org.auth.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.auth.beans.Account;
import org.auth.beans.RegisterAccount;
import org.auth.beans.ValidateAccount;
import org.auth.server.main.SpringBootRestApplication;
import org.auth.util.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class act as a TDD implementation, where it will test all the functionality of the application.
 * @author suresh kumar
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RestController
public class AuthenticationControllerTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void registerTestCase1() {
		Account request = new Account();
		request.setUserName("test@example.com");
		
		ResponseEntity<RegisterAccount> response = restTemplate.postForEntity(createURLWithPort("/auth/v1/2fa/ga/register"), request, RegisterAccount.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getSecretKey()).isNotEmpty();
		assertThat(response.getBody().getErrorMessage()).isBlank();
	}
	
	@Test
	public void registerTestCase2() {
		Account request = new Account();
		
		ResponseEntity<RegisterAccount> response = restTemplate.postForEntity(createURLWithPort("/auth/v1/2fa/ga/register"), request, RegisterAccount.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getSecretKey()).isBlank();
		assertThat(response.getBody().getErrorMessage()).isEqualTo(Constants.USERNAME_ERROR_MSG_1);
	}
	
	@Test
	public void registerTestCase3() {
		ResponseEntity<RegisterAccount> response = restTemplate.postForEntity(createURLWithPort("/auth/v1/2fa/ga/register"), null, RegisterAccount.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void validateTestCase1() {
		Account request = new Account();
		request.setUserName("test@example.com");
		request.setOtp("123456");
		
		ResponseEntity<ValidateAccount> response = restTemplate.postForEntity(createURLWithPort("/auth/v1/2fa/ga/validate"), request, ValidateAccount.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getStatus()).isNotEmpty();
		assertThat(response.getBody().getErrorMessage()).isBlank();
	}
	
	@Test
	public void validateTestCase2() {
		Account request = new Account();
		
		ResponseEntity<ValidateAccount> response = restTemplate.postForEntity(createURLWithPort("/auth/v1/2fa/ga/validate"), request, ValidateAccount.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getStatus()).isBlank();
		assertThat(response.getBody().getErrorMessage()).isEqualTo(Constants.USERNAME_OTP_ERROR_MSG_1);
	}
	
	@Test
	public void validateTestCase3() {
		Account request = new Account();
		request.setUserName("test@example.com");
		
		ResponseEntity<ValidateAccount> response = restTemplate.postForEntity(createURLWithPort("/auth/v1/2fa/ga/validate"), request, ValidateAccount.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getStatus()).isBlank();
		assertThat(response.getBody().getErrorMessage()).isEqualTo(Constants.OTP_ERROR_MSG_1);
	}
	
	@Test
	public void validateTestCase4() {
		Account request = new Account();
		request.setUserName("test@example.com");
		request.setOtp("12345");
		
		ResponseEntity<ValidateAccount> response = restTemplate.postForEntity(createURLWithPort("/auth/v1/2fa/ga/validate"), request, ValidateAccount.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getStatus()).isBlank();
		assertThat(response.getBody().getErrorMessage()).isEqualTo(Constants.OTP_ERROR_MSG_2);
	}
	
	@Test
	public void validateTestCase5() {
		Account request = new Account();
		request.setOtp("12345");
		
		ResponseEntity<ValidateAccount> response = restTemplate.postForEntity(createURLWithPort("/auth/v1/2fa/ga/validate"), request, ValidateAccount.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getStatus()).isBlank();
		assertThat(response.getBody().getErrorMessage()).isEqualTo(Constants.USERNAME_OTP_ERROR_MSG_2);
	}
		
	@Test
	public void validateTestCase6() {
		Account request = new Account();
		request.setOtp("123456");
		
		ResponseEntity<ValidateAccount> response = restTemplate.postForEntity(createURLWithPort("/auth/v1/2fa/ga/validate"), request, ValidateAccount.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getStatus()).isBlank();
		assertThat(response.getBody().getErrorMessage()).isEqualTo(Constants.USERNAME_ERROR_MSG_1);
	}
	
	@Test
	public void validateTestCase7() {
		Account request = new Account();
		request.setUserName("test1@example.com");
		request.setOtp("123456");
		
		ResponseEntity<ValidateAccount> response = restTemplate.postForEntity(createURLWithPort("/auth/v1/2fa/ga/validate"), request, ValidateAccount.class);
		
		assertThat(response.getBody().getErrorMessage()).isEqualTo(Constants.USERNAME_ERROR_MSG_2);		
	}
	
	@Test
	public void validateTestCase8() {
		
		ResponseEntity<ValidateAccount> response = restTemplate.postForEntity(createURLWithPort("/auth/v1/2fa/ga/validate"), null, ValidateAccount.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);		
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
}
