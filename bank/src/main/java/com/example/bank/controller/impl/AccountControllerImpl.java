package com.example.bank.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.controller.AccountController;
import com.example.bank.dto.request.RegistrationRequest;
import com.example.bank.service.RegistrationService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/registration")
public class AccountControllerImpl implements AccountController{
	
	private final RegistrationService registrationService;
	
	@PostMapping
	public ResponseEntity<String> registration(@Valid @RequestBody RegistrationRequest request){
		System.out.println(request);
		registrationService.creatUset(request);
		return ResponseEntity.status(HttpStatus.OK).body("Вы успешно зарегестрировались");
	}

}
