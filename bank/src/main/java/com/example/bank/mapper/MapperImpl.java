package com.example.bank.mapper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

import com.example.bank.dto.DepositeDto;
import com.example.bank.dto.OperationDto;
import com.example.bank.model.Deposite;
import com.example.bank.model.Operation;

@Component
public class MapperImpl implements Mapper{

	@Override
	public OperationDto toOperationDto(Operation operation) {
		LocalDate created = LocalDate.ofInstant(Instant.ofEpochSecond(operation.getCreated()), ZoneId.systemDefault());
		Deposite payer = operation.getPayer();
		Deposite recipient = operation.getRecipient();
		OperationDto dto = OperationDto.builder()
				.created(created)
				.recipientId(recipient == null ? null : recipient.getId())
				.amount(operation.getAmount())
				.typeOperation(operation.getType())
				.payerId(payer == null ? null : payer.getId())
				.build();
		
		return dto;
	}

	@Override
	public DepositeDto toDepositeDto(Deposite deposite) {
		LocalDate created = LocalDate.ofInstant(Instant.ofEpochSecond(deposite.getCreated()), ZoneId.systemDefault());
		DepositeDto depositeDto = DepositeDto.builder()
				.created(LocalDate.now())
				.owner_id(deposite.getOwner().getId())
				.amoutMoney(deposite.getAmountMoney())
				.id(deposite.getId())
				.type(deposite.getType())
				.build();
		return depositeDto;
	}

}
