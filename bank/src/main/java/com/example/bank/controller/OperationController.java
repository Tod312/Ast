package com.example.bank.controller;

import java.util.List;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bank.dto.DepositeDto;
import com.example.bank.dto.OperationDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Operation Controller",
description = "работает операциями депозита")
public interface OperationController {
	@Operation(
			summary = "get запрос на получение всех операции",
			description = "выдаёт историю операций, совершенных с данным депозитом"
			)
	@ApiResponses(
		      value = {
		    		  @ApiResponse(
    			              responseCode = "200",
    			              description = "Ok",
    			              content = @Content(
    		    			      schema = @Schema(implementation = OperationDto.class),
    		    			      examples = {
  										@ExampleObject(value = 
  												"""
  												{
  												    "created": "2024-10-10",
											        "payerId": null,
											        "recipientId": 1,
											        "amount": 100.67,
											        "typeOperation": "REPLENISHMENT"
  												}
  												"""
  												)
  								})),
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
													    "instance": "/deposite",
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
  													"instance": "/deposite"
  												}
  												"""
  												)
  								}))
		      }
		  )
	
	@GetMapping
	ResponseEntity<List<OperationDto>> getAllOperations(@RequestParam Long depositeId);
}
