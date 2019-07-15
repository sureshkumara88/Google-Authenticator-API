# Google Authenticator API 

This project is developed to register and validate our accounts with google authenticator API, which is a Java server library that implements the Time-based One-time Password (TOTP) algorithm specified in RFC 6238. 


The TOTP will change in every 30 seconds interval for an account. 


## Getting Started

This project is created by using Core Java with Spring Boot framework in Maven. This allows you to do below mentioned items,

● /auth/v1/2fa/ga/register - Returns a randomized Google Authenticator secret key generated on server side as a String.

● /auth/v1/2fa/ga/validate - Take the secret key a code generated from the Google Authenticator app. Should respond if the validation was successful or not.


### Prerequisites

Need maven repository in your local and download all the dependencies that are specified in pom.xml file.


### Installing

Once you imported the system, you need to compile and install the project into your local maven repository. And, then you can add this artifact as a dependency for your maven project.

		<dependency>
			<groupId>org.auth.service</groupId>
			<artifactId>spring-boot-rest</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>


## Running the tests
		
You need to execute below test cases from AuthenticationControllerTests class,

		registerTestCase1();
		registerTestCase2();
		registerTestCase3();
		validateTestCase1();
		validateTestCase2();
		validateTestCase3();
		validateTestCase4();
		validateTestCase5();
		validateTestCase6();
		validateTestCase7();
		validateTestCase8();
		

## Built With
* [Maven] (https://maven.apache.org/) - Dependency Management


## Author

Suresh Kumar - [Google Authenticator API]( https://github.com/sureshkumara88/Google-Authenticator-API.git)


