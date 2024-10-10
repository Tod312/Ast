/**
 * 
 */
package com.example.bank.model;

/**
 * 
 */
public enum CurrencyType {

	RUB("Российский рубль");
	
	private String description;
	

	public String getDescription() {
		return description;
	}

	private CurrencyType(String description) {
		this.description = description;
	}
	
}
