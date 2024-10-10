package com.example.bank.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record ReplenishmentRequest(
		@NotNull Long depositeId,
		@NotNull BigDecimal amountOfMoney
		) {

}
