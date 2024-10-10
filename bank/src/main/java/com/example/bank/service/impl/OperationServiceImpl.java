package com.example.bank.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.dto.OperationDto;
import com.example.bank.mapper.Mapper;
import com.example.bank.model.Operation;
import com.example.bank.repository.OperationRepository;
import com.example.bank.service.OperationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OperationServiceImpl implements OperationService{

	private final OperationRepository operationRepository;
	private final Mapper mapper;
	
	@Override
	public List<OperationDto> getAllByDeposite(Long depositeId) {
		return operationRepository.findByDepositeId(depositeId).stream()
				.map(operation -> mapper.toOperationDto(operation)).toList();
	}

	@Override
	public Operation create(Operation operation) {
		return operationRepository.save(operation);
	}

	@Override
	public List<Operation> getAll() {
		Iterable<Operation> iter = operationRepository.findAll();
		List<Operation> operations = new ArrayList<Operation>();
		iter.forEach(o -> operations.add(o));
		return operations;
	}

}
