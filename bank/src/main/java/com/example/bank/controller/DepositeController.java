package com.example.bank.controller;

import java.util.List;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bank.dto.DepositeDto;
import com.example.bank.dto.request.RegistrationRequest;
import com.example.bank.dto.request.ReplenishmentRequest;
import com.example.bank.dto.request.TransferRequest;
import com.example.bank.dto.request.WithdrawRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Tag(name = "Deposite Controller",
description = "Производит операции, связанный с депозитом: снятие, пополнение, перевод")
public interface DepositeController {
	@Operation(
			summary = "get запрос для получения депозитов",
			description = "выдаёт все депозиты, которе принадлежать определённому пользователю"
			)
	@ApiResponses(
		      value = {
		    		  @ApiResponse(
    			              responseCode = "200",
    			              description = "Ok",
    			              content = @Content(
    		    			      schema = @Schema(implementation = DepositeDto.class),
    		    			      examples = {
  										@ExampleObject(value = 
  												"""
  												{
  												    "id": 1,
												    "owner_id": 1,
												    "type": RUB,
												    "amoutMoney": 10.67,
												    "created": "2024-10-10"
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
													    "instance": "/deposite/all",
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
  													"instance": "/deposite/all""
  												}
  												"""
  												)
  								}))
		      }
		  )
	@GetMapping("/all")
	public ResponseEntity<List<DepositeDto>> getAllDeposites(@NotBlank @RequestParam String login);
	
	@Operation(
			summary = "put запрос на перевод средств",
			description = "переводит средства с одного депозита на другой и возвращает депозит отправителя",
					requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
							description = "запрос для перевода средств", 
							required = true,
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(
											implementation = TransferRequest.class),
									examples = {
											@ExampleObject(value = 
													"""
													{
													    "payerDepositeId": "1",
													    "recipientDepositeId": 2,
													    "amountOfmoney": 5.50,
													    "payerPinCode": 3245
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
    		    			      schema = @Schema(implementation = DepositeDto.class),
    		    			      examples = {
  										@ExampleObject(value = 
  												"""
  												{
  												    "id": 1,
												    "owner_id": 1,
												    "type": RUB,
												    "amoutMoney": 10.67,
												    "created": "2024-10-10"
  												}
  												"""
  												)
  								})),
		    		  @ApiResponse(
    			              responseCode = "403",
    			              description = "Forbidden",
    			              content = @Content(
    			                  mediaType = "application/json",
    			                  schema = @Schema(implementation = ProblemDetail.class),
    			                  examples = {
    										@ExampleObject(value = 
    												"""
    												{
    												    "type": "about:blank",
													    "title": "Forbidden",
													    "status": 400,
													    "detail": "ошибка валидации",
													    "instance": "/deposite/transfer"
    												}
    												"""
    												)
    								})),
		    		  @ApiResponse(
    			              responseCode = "404",
    			              description = "Not found",
    			              content = @Content(
    			                  mediaType = "application/json",
    			                  schema = @Schema(implementation = ProblemDetail.class),
    			                  examples = {
    										@ExampleObject(value = 
    												"""
    												{
    												    "type": "about:blank",
													    "title": "Not Found",
													    "status": 400,
													    "detail": "ошибка валидации",
													    "instance": "/deposite/transfer"
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
													    "instance": "/deposite/transfer",
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
  													"instance": "/deposite/transfer"
  												}
  												"""
  												)
  								}))
		      }
		  )
	
	@PutMapping("/transfer")
	public ResponseEntity<DepositeDto> transfer(@Valid @RequestBody TransferRequest request);
	
	@Operation(
			summary = "put запрос на снятие средств ",
			description = "запрос на снятие средств с депозита",
					requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
							description = "запрос для снятия средств с депозита", 
							required = true,
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(
											implementation = WithdrawRequest.class),
									examples = {
											@ExampleObject(value = 
													"""
													{
													    "depositeId": 1,
													    "amountOfMoney": 90,
													    "pinCode": 3245
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
    		    			      schema = @Schema(implementation = DepositeDto.class),
    		    			      examples = {
  										@ExampleObject(value = 
  												"""
  												{
  												    "id": 1,
												    "owner_id": 1,
												    "type": RUB,
												    "amoutMoney": 10.67,
												    "created": "2024-10-10"
  												}
  												"""
  												)
  								})),
		    		  @ApiResponse(
    			              responseCode = "403",
    			              description = "Forbidden",
    			              content = @Content(
    			                  mediaType = "application/json",
    			                  schema = @Schema(implementation = ProblemDetail.class),
    			                  examples = {
    										@ExampleObject(value = 
    												"""
    												{
    												    "type": "about:blank",
													    "title": "Forbidden",
													    "status": 400,
													    "detail": "ошибка валидации",
													    "instance": "/deposite/withdraw"
    												}
    												"""
    												)
    								})),
		    		  @ApiResponse(
    			              responseCode = "404",
    			              description = "Not found",
    			              content = @Content(
    			                  mediaType = "application/json",
    			                  schema = @Schema(implementation = ProblemDetail.class),
    			                  examples = {
    										@ExampleObject(value = 
    												"""
    												{
    												    "type": "about:blank",
													    "title": "Not Found",
													    "status": 400,
													    "detail": "ошибка валидации",
													    "instance": "/deposite/withdraw"
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
													    "instance": "/deposite/withdraw",
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
  													"instance": "/deposite/withdraw"
  												}
  												"""
  												)
  								}))
		      }
		  )
	
	@PutMapping("/withdraw")
	public ResponseEntity<DepositeDto> withdraw(@Valid @RequestBody WithdrawRequest request);
	
	@Operation(
			summary = "put запрос на пополнение депозита ",
			description = "запрос на пополнение баланса депозита",
					requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
							description = "запрос на пополнение баланса депозита", 
							required = true,
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(
											implementation = ReplenishmentRequest.class),
									examples = {
											@ExampleObject(value = 
													"""
													{
													    "depositeId": 1,
														"amountOfMoney": 100.67
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
    		    			      schema = @Schema(implementation = DepositeDto.class),
    		    			      examples = {
  										@ExampleObject(value = 
  												"""
  												{
  												    "id": 1,
												    "owner_id": 1,
												    "type": RUB,
												    "amoutMoney": 10.67,
												    "created": "2024-10-10"
  												}
  												"""
  												)
  								})),
		    		  @ApiResponse(
    			              responseCode = "404",
    			              description = "Not found",
    			              content = @Content(
    			                  mediaType = "application/json",
    			                  schema = @Schema(implementation = ProblemDetail.class),
    			                  examples = {
    										@ExampleObject(value = 
    												"""
    												{
    												    "type": "about:blank",
													    "title": "Not Found",
													    "status": 400,
													    "detail": "ошибка валидации",
													    "instance": "/deposite/replenishment"
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
													    "instance": "/deposite/replenishment",
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
  													"instance": "/deposite/replenishment"
  												}
  												"""
  												)
  								}))
		      }
		  )
	
	@PutMapping("/replenishment")
	public ResponseEntity<DepositeDto> replenishment(@Valid @RequestBody ReplenishmentRequest request);
}
