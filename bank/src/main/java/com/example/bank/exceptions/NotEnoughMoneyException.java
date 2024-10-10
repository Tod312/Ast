package com.example.bank.exceptions;

public class NotEnoughMoneyException extends RuntimeException{

	public NotEnoughMoneyException() {
		super();
	}

	public NotEnoughMoneyException(String message) {
		super(message);
	}

	
}
