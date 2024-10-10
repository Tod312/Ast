package com.example.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.bank.model.Account;
import com.example.bank.model.Deposite;

public interface DepositeRepository extends CrudRepository<Deposite, Long>{

	@Query("select d from Deposite d where d.owner = :owner")
	List<Deposite> findByOwner(Account owner);
}
