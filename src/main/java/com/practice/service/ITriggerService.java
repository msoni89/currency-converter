package com.practice.service;

import java.util.Optional;

import com.practice.dto.ResponseDto;

public interface ITriggerService {

	Optional<ResponseDto> trigger(String fromCurrency, String toCurrency);

}
