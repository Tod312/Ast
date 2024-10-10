package com.example.bank.service.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.bank.dto.request.RegistrationRequest;
import com.example.bank.exceptions.BadBirthdayException;
import com.example.bank.model.Account;
import com.example.bank.model.CurrencyType;
import com.example.bank.model.Deposite;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.DepositeRepository;
import com.example.bank.service.AccountService;
import com.example.bank.service.RegistrationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{

	private final AccountService accountService;
	private final DepositeRepository depositeRepository;
	
	@Override
	public void creatUset(RegistrationRequest request) {
		LocalDate now = LocalDate.now();
		LocalDate min = now.minusYears(14);
		if(request.birthday().isAfter(min)) {
			throw new BadBirthdayException("Чтобы зарегестрироваться и открыть счёт Вы должны быть старше 14 лет");
		}
		if(request.birthday().isAfter(min)) {
			throw new BadBirthdayException("Чтобы зарегестрироваться и открыть счёт Вы должны быть старше 14 лет");
		}
		Account account = Account.builder().login(request.login()).pincode(request.pinCode()).build();
		account = accountService.create(account);
		Deposite newDeposite = Deposite.builder().owner(account).amountMoney(BigDecimal.ZERO).created(Instant.now().getEpochSecond())
				.type(CurrencyType.RUB).build();
		depositeRepository.save(newDeposite);
	}


}
