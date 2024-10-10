package com.example.bank.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.controller.DepositeController;
import com.example.bank.dto.DepositeDto;
import com.example.bank.dto.request.ReplenishmentRequest;
import com.example.bank.dto.request.TransferRequest;
import com.example.bank.dto.request.WithdrawRequest;
import com.example.bank.model.Account;
import com.example.bank.model.Deposite;
import com.example.bank.service.AccountService;
import com.example.bank.service.DepositeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/deposite")
public class DepositeControllerImpl implements DepositeController{

	private final DepositeService depositeService;
	private final AccountService accountService;
	
	@GetMapping("/all")
	public ResponseEntity<List<DepositeDto>> getAllDeposites(@NotBlank @RequestParam String login) {
		Account account = accountService.getByLogin(login);
		List<DepositeDto> deposites = depositeService.getAllByAccount(account);
		return ResponseEntity.ok(deposites);
	}
	
	@PutMapping("/transfer")
	public ResponseEntity<DepositeDto> transfer(@Valid @RequestBody TransferRequest request){
		DepositeDto payer = depositeService.transefer(request.payerDepositeId(), request.recipientDepositeId(), request.payerPinCode(), request.amountOfmoney());
		return ResponseEntity.ok(payer);
	}
	
	@PutMapping("/withdraw")
	public ResponseEntity<DepositeDto> withdraw(@Valid @RequestBody WithdrawRequest request){
		DepositeDto withdraw = depositeService.withdraw(request.depositeId(), request.amountOfMoney(), request.pinCode());
		return ResponseEntity.ok(withdraw);
	}
	
	@PutMapping("/replenishment")
	public ResponseEntity<DepositeDto> replenishment(@Valid @RequestBody ReplenishmentRequest request){
		DepositeDto replenishment = depositeService.replenishment(request.depositeId(), request.amountOfMoney());
		return ResponseEntity.ok(replenishment);
	}
}
