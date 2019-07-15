package org.auth.server.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * This class is used to start the Spring Boot REST Webservice
 * @author suresh kumar
 *
 */
@EnableHystrix
@SpringBootApplication(scanBasePackages = {"org.auth"})
public class SpringBootRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApplication.class, args);
	}

}

