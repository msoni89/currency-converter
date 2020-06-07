package com.practice.service;

import java.util.List;
import java.util.Optional;

import com.practice.dto.CurrencyDto;
import com.practice.enums.Frequency;
import com.practice.model.DashboardData;
import com.practice.model.RealTimeCurrencyExRate;

public interface ICurrencyService {

	Optional<List<CurrencyDto>> getAllAvailableCurrency();

	void save(RealTimeCurrencyExRate currency);

	Optional<RealTimeCurrencyExRate> findRealtimeCurrencyExRate(String fromCurrency, String toCurrency);

	Optional<DashboardData> findDashboardCurrencyExRate(String fromCurrency, String toCurrency, Frequency frequency);

}
