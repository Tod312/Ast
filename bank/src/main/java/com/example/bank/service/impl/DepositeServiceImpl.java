package com.example.bank.service.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bank.dto.DepositeDto;
import com.example.bank.exceptions.AccessDeniedException;
import com.example.bank.exceptions.DepositeNotFoundException;
import com.example.bank.exceptions.NotEnoughMoneyException;
import com.example.bank.mapper.Mapper;
import com.example.bank.model.Account;
import com.example.bank.model.Deposite;
import com.example.bank.model.Operation;
import com.example.bank.model.TypeOperation;
import com.example.bank.repository.DepositeRepository;
import com.example.bank.service.DepositeService;
import com.example.bank.service.OperationService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class DepositeServiceImpl implements DepositeService{

	private final DepositeRepository depositeRepository;
	private final OperationService operationService;
	private final Mapper mapper;
	
	@Transactional(readOnly = true)
	@Override
	public Deposite getById(Long id) {
		return depositeRepository.findById(id).orElseThrow(() -> new DepositeNotFoundException("Deposite с данным id=%d не существует".formatted(id)));
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<DepositeDto> getAllByAccount(Account account) {
		return depositeRepository.findByOwner(account).stream()
				.map(deposite -> mapper.toDepositeDto(deposite)).toList();
	}

	@Transactional
	@Override
	public DepositeDto transefer(Long payer, Long recipient, String pinCode, BigDecimal amontOfMoney) {
		Deposite payerDep = getById(payer);
		Deposite recipientDep = getById(recipient);
		if(!payerDep.getOwner().getPincode().equals(pinCode)) {
			throw new AccessDeniedException("Неверный пароль от deposite отправителя");
		}
		BigDecimal newForPayer = payerDep.getAmountMoney().subtract(amontOfMoney);
		if(newForPayer.compareTo(BigDecimal.ZERO) < 0) {
			throw new NotEnoughMoneyException("Недостаточно средств для перевода");
		}
		BigDecimal newForReciptiien = recipientDep.getAmountMoney().add(amontOfMoney);
		payerDep.setAmountMoney(newForPayer);
		recipientDep.setAmountMoney(newForReciptiien);
		payerDep = depositeRepository.save(payerDep);
		depositeRepository.save(recipientDep);
		Operation operation = Operation.builder().payer(payerDep).recipient(recipientDep).amount(amontOfMoney)
				.created(Instant.now().getEpochSecond()).type(TypeOperation.TRANSFER).build();
		operationService.create(operation);
		log.info("Деньги были успешно переведены с одного счета на другой");
		return mapper.toDepositeDto(payerDep);
	}

	@Transactional
	@Override
	public DepositeDto withdraw(Long depositeId,  BigDecimal amountOfMoney, String pinCode) {
		Deposite deposite = getById(depositeId);
		if(!deposite.getOwner().getPincode().equals(pinCode)) {
			throw new AccessDeniedException("Неверный пароль от deposite");
		}
		BigDecimal newForPayer = deposite.getAmountMoney().subtract(amountOfMoney);
		if(newForPayer.compareTo(BigDecimal.ZERO) < 0) {
			throw new NotEnoughMoneyException("Недостаточно средств для снятия");
		}
		deposite.setAmountMoney(newForPayer);
		deposite = depositeRepository.save(deposite);
		Operation operation = Operation.builder().payer(deposite).amount(amountOfMoney).created(Instant.now().getEpochSecond())
				.type(TypeOperation.WITHDRAW).build();
		operationService.create(operation);
		
		return mapper.toDepositeDto(deposite);
	}

	@Transactional
	@Override
	public DepositeDto replenishment(Long depositeId, BigDecimal amountOfMoney) {
		Deposite deposite = getById(depositeId);
		BigDecimal newAmountOfMoney = deposite.getAmountMoney().add(amountOfMoney);
		deposite.setAmountMoney(newAmountOfMoney);
		deposite = depositeRepository.save(deposite);
		Operation operation = Operation.builder().recipient(deposite).amount(amountOfMoney).type(TypeOperation.REPLENISHMENT).created(Instant.now().getEpochSecond()).build();
		operationService.create(operation);
		return mapper.toDepositeDto(deposite);
	}
	
	
}
