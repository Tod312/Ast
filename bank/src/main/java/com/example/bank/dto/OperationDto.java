package com.example.bank.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.bank.model.TypeOperation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationDto {
	private LocalDate created;
	private Long payerId;
	private Long recipientId;
	private BigDecimal amount;
	private TypeOperation typeOperation;

}
