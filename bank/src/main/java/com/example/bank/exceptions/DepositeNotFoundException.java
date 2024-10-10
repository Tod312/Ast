package com.example.bank.exceptions;

public class DepositeNotFoundException extends RuntimeException{

	public DepositeNotFoundException() {
		super();
	}

	public DepositeNotFoundException(String message) {
		super(message);
	}

	
	
}
