package com.example.bank.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.bank.model.CurrencyType;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DepositeDto {

	private Long id;
	private Long owner_id;
	private CurrencyType type;
	private BigDecimal amoutMoney;
	private LocalDate created;
}
