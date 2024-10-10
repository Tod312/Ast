package com.example.bank.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransferRequest(
		@NotNull(message = "payerDepositeId не может быть null") Long payerDepositeId, 
		@NotNull(message = "recipientDepositeId не может быть null") Long recipientDepositeId, 
		@NotNull(message = "amountOfmoney не может быть null") BigDecimal amountOfmoney, 
		@NotBlank(message = "payerPinCode не может быть пустым или содержать только пробелы") String payerPinCode) {
}
