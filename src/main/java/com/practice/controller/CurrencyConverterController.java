package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.practice.constant.Constant;
import com.practice.dto.CurrencyDto;
import com.practice.enums.Frequency;
import com.practice.model.DashboardData;
import com.practice.model.RealTimeCurrencyExRate;
import com.practice.service.ICurrencyService;

@RestController
@RequestMapping("/v1/cc")
public class CurrencyConverterController {

	@Autowired
	private ICurrencyService currencyService;

	/**
	 * This method return available currencies
	 * @return List<CurrencyDto>
	 */
	@GetMapping("/")
	public ResponseEntity<List<CurrencyDto>> currency() {
		return currencyService.getAllAvailableCurrency().map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constant.NOT_FOUND_ERROR_MESSAGE));
	}

	/**
	 * This method return real time currency ex rate based on passed from and to currency
	 * @param fromCurrency
	 * @param toCurrency
	 * @return RealTimeCurrencyExRate
	 */
	@GetMapping("/from/{from}/to/{to}")
	public ResponseEntity<RealTimeCurrencyExRate> realTimeCurrencyRate(@PathVariable("from") String fromCurrency,
			@PathVariable("to") String toCurrency) {
		return currencyService.findRealtimeCurrencyExRate(fromCurrency, toCurrency).map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constant.NOT_FOUND_ERROR_MESSAGE));
	}

	/**
	 *  This method return dashboard data based from and to currency
	 * @param fromCurrency
	 * @param toCurrency
	 * @param frequency
	 * @return DashboardData
	 */
	@GetMapping("/dashboard/from/{from}/to/{to}/frequency/{frequency}")
	public ResponseEntity<DashboardData> dashboardCurrency(@PathVariable("from") String fromCurrency,
			@PathVariable("to") String toCurrency,
			@PathVariable("frequency") Frequency frequency) {
		return currencyService.findDashboardCurrencyExRate(fromCurrency, toCurrency, frequency).map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constant.NOT_FOUND_ERROR_MESSAGE));
	}
}
