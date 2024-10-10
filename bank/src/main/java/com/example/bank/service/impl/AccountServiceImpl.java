package com.example.bank.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.exceptions.LoginAlreadyExistsException;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import com.example.bank.service.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

	private final AccountRepository repository;
	
	@Override
	public Account getByLogin(String login) {
		return repository.findByLogin(login).orElseThrow(() -> new AccountNotFoundException("Account с таким login: %s не существует".formatted(login)));
	}

	@Override
	public List<Account> getAll() {
		Iterable<Account> accounts = repository.findAll();
		List<Account> listAccount = new ArrayList<Account>();
		accounts.forEach(acount -> listAccount.add(acount));
		return listAccount;
	}

	@Override
	public Account create(Account account) {
		Optional<Account> oldAccount = repository.findByLogin(account.getLogin());
		if(oldAccount.isPresent()) {
			throw new LoginAlreadyExistsException("данный логин уже занят");
		}
		return repository.save(account);
	}
	
	

}
