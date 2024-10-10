package com.example.bank.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "deposite")
public class Deposite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "id")
	private Account owner;
	
	@Enumerated(EnumType.STRING)
	private CurrencyType type;
	private BigDecimal amountMoney;
	private Long created;
}
