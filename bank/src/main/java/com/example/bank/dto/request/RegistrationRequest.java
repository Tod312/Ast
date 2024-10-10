package com.example.bank.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
		@NotEmpty(message = "поле login не может быть пустым") String login, 
		@Digits(integer = 4, fraction = 0, message = "Поле pincode не может содержать больше 4 символов и может содержать только цифры") String pinCode, 
		@JsonFormat(pattern = "dd.MM.yyyy")
		@JsonSerialize(using = LocalDateSerializer.class)
		@JsonDeserialize(using = LocalDateDeserializer.class)
		LocalDate birthday) {

}
