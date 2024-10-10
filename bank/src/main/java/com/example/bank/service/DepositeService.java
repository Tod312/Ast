package com.example.bank.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.bank.dto.DepositeDto;
import com.example.bank.model.Account;
import com.example.bank.model.Deposite;

public interface DepositeService {

	List<DepositeDto> getAllByAccount(Account account);
	
	Deposite getById(Long id);
	
	DepositeDto transefer(Long payer, Long recipient, String pinCode, BigDecimal amontOfMoney);
	
	DepositeDto withdraw(Long depositeId, BigDecimal amounOfMoney, String pinCode);
	
	DepositeDto replenishment(Long depositeId,BigDecimal amountOfMoney);
}
