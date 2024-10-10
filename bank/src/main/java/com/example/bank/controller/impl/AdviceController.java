package com.example.bank.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.bank.exceptions.AccessDeniedException;
import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.exceptions.BadBirthdayException;
import com.example.bank.exceptions.DepositeNotFoundException;
import com.example.bank.exceptions.NotEnoughMoneyException;

@ControllerAdvice
public class AdviceController {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "ошибка валидации");
		detail.setProperty("errors", ex.getFieldErrors().stream().map(f -> f.getDefaultMessage()).toList());
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detail);
	}
	
	@ExceptionHandler(BadBirthdayException.class)
	public ResponseEntity<ProblemDetail> handleBadBirthdayException(BadBirthdayException ex){
		ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "ошибка валидации");
		detail.setDetail(ex.getMessage());
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detail);
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ProblemDetail> handleAccountNotFoundException(AccountNotFoundException ex){
		ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "ошибка валидации");
		detail.setDetail(ex.getMessage());
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detail);
	}
	
	@ExceptionHandler(DepositeNotFoundException.class)
	public ResponseEntity<ProblemDetail> DepositeNotFoundException(DepositeNotFoundException ex){
		ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "ошибка валидации");
		detail.setDetail(ex.getMessage());
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detail);
	}
	
	@ExceptionHandler(NotEnoughMoneyException.class)
	public ResponseEntity<ProblemDetail> handleNotEnoughMoneyException(NotEnoughMoneyException ex){
		ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "ошибка валидации");
		detail.setDetail(ex.getMessage());
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detail);
	}
	
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ProblemDetail> handleAccessDeniedException(AccessDeniedException ex){
		ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, "ошибка валидации");
		detail.setDetail(ex.getMessage());
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(detail);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleException(Exception ex){
		ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "ошибка валидации");
		detail.setDetail(ex.getMessage());
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(detail);
	}

}
