package com.example.bank.service;

import java.util.List;

import com.example.bank.dto.OperationDto;
import com.example.bank.model.Operation;

public interface OperationService {

	List<OperationDto> getAllByDeposite(Long depositeId);
	
	List<Operation> getAll();
	
	Operation create(Operation operation);
	
}
