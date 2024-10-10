package com.example.bank.service;

import java.util.List;

import com.example.bank.model.Account;

public interface AccountService {

	Account getByLogin(String login);
	
	List<Account> getAll();
	
	Account create(Account account);
}
