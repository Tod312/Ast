package com.example.bank.mapper;

import com.example.bank.dto.DepositeDto;
import com.example.bank.dto.OperationDto;
import com.example.bank.model.Deposite;
import com.example.bank.model.Operation;

public interface Mapper {
	
	OperationDto toOperationDto(Operation operation);
	
	DepositeDto toDepositeDto(Deposite deposite);

}
