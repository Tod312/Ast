package com.example.bank.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record WithdrawRequest(
		@NotNull Long depositeId,
		@NotNull BigDecimal amountOfMoney,
		@NotEmpty String pinCode
		){
}
