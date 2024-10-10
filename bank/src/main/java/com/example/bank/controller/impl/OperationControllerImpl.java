package com.example.bank.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.example.bank.controller.OperationController;
import com.example.bank.dto.OperationDto;
import com.example.bank.model.Operation;
import com.example.bank.service.OperationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("operations")
public class OperationControllerImpl implements OperationController{

	private final OperationService operationService;
	
	@GetMapping
	public ResponseEntity<List<OperationDto>> getAllOperations(@RequestParam Long depositeId){
		List<OperationDto> operatins = operationService.getAllByDeposite(depositeId);
		return ResponseEntity.ok(operatins);
	}
}
