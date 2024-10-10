package com.example.bank.controller;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bank.dto.request.RegistrationRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name ="Account Controller", 
description = "controller to create account and get account")
public interface AccountController {
	
	@Operation(
			summary = "registrer a new user",
			description = "create a new account and deposite",
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
					description = "запрос для регистрации пользователя", 
					required = true,
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(
									implementation = RegistrationRequest.class),
							examples = {
									@ExampleObject(value = 
											"""
											{
											    "login": "hello", 
												"pinCode": 9872, 
												"birthday": "17.09.1996"
											}
											"""
											)
							}
					)
					)
			)
	@ApiResponses(
		      value = {
		    		  @ApiResponse(
    			              responseCode = "200",
    			              description = "Ok",
    			              content = @Content(
    		    			      schema = @Schema(implementation = String.class))),
		    		  
		    		  @ApiResponse(
    			              responseCode = "400",
    			              description = "Bad Request",
    			              content = @Content(
    			                  mediaType = "application/json",
    			                  schema = @Schema(implementation = ProblemDetail.class),
    			                  examples = {
    										@ExampleObject(value = 
    												"""
    												{
    												    "type": "about:blank",
													    "title": "Bad Request",
													    "status": 400,
													    "detail": "ошибка валидации",
													    "instance": "/registration",
													    "errors": [
													        "Поле pincode не может содержать больше 4 символов и может содержать только цифры"
													    ]
    												}
    												"""
    												)
    								})),
    			      @ApiResponse(
    			              responseCode = "500",
    			              description = "Internal Server Error",
    			              content = @Content(
    			                  mediaType = "application/json",
    			                  schema = @Schema(implementation = ProblemDetail.class),
    			                  examples = {
  										@ExampleObject(value = 
  												"""
  												{
  												    "type": "about:blank",
													"title": "Internal Server Error",
													"status": 500,
													"detail": "JSON parse error: Cannot deserialize value of type `java.time.LocalDate` from String 17/09/1996: Failed to deserialize java.time.LocalDate: (java.time.format.DateTimeParseException) Text '17/09/1996' could not be parsed at index 2",
  													"instance": "/registration"
  												}
  												"""
  												)
  								}))
		      }
		  )
	@PostMapping
	ResponseEntity<String> registration(@Valid @RequestBody RegistrationRequest request);
}
