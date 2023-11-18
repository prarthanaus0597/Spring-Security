package com.auth.oauth.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@SpringBootApplication
@RestController
@RequestMapping(value = "/")
public class OauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}


	@GetMapping("welcome")
	public ResponseEntity getLogin(){
		return ResponseEntity.status(HttpStatus.OK).body("Oauth Login Successful!!");

	}

}
