package com.example.bank.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.bank.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{

	Optional<Account> findByLogin(String login);
}
