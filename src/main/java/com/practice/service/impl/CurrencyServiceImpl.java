package com.practice.service.impl;

import com.practice.constant.Constant;
import com.practice.dto.CurrencyDto;
import com.practice.enums.Frequency;
import com.practice.model.DashboardData;
import com.practice.model.RealTimeCurrencyExRate;
import com.practice.repository.ICurrencyRepository;
import com.practice.repository.IDashboardRepository;
import com.practice.service.ICurrencyService;
import com.practice.utility.KeyGeneratorUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements ICurrencyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);

	@Autowired
	private ICurrencyRepository currencyRepository;

	@Autowired
	private IDashboardRepository dashboardRepository;

	@Override
	public Optional<List<CurrencyDto>> getAllAvailableCurrency() {
		return Optional.of(getCurrencies());
	}

	/**
	 * This method return supported Currencies [USD, EUR, GBP]
	 * @return @List<CurrencyDto>
	 */
	private List<CurrencyDto> getCurrencies() {
		LOGGER.info(Constant.ENTERED);
		List<CurrencyDto> list = new ArrayList<CurrencyDto>();
		list.add(new CurrencyDto("United States Dollar", "USD", "$"));
		list.add(new CurrencyDto("Euro", "EUR", "€"));
		list.add(new CurrencyDto("Great Britain Pound", "GBP", "£"));
		return list;
	}

	/**
	 * This method save realtime currency rate into redis database
	 * @param currency
	 */
	@Override
	public void save(RealTimeCurrencyExRate currency) {
		LOGGER.info(Constant.ENTERED);
		currencyRepository.save(currency);
		LOGGER.info("Currency object inserted");
	}

	/**
	 * This method return latest currency ex rate sorted by last fetched time.
	 * @param fromCurrency
	 * @param toCurrency
	 * @return
	 */
	@Override
	public Optional<RealTimeCurrencyExRate> findRealtimeCurrencyExRate(String fromCurrency, String toCurrency) {
		LOGGER.info(Constant.ENTERED);
		String key = KeyGeneratorUtility.generateKey(toCurrency, fromCurrency);
		LOGGER.info("Key generated for request {}",key);
		return currencyRepository.findFirstByKeyOrderByFetchedTimeDesc(key);
	}

	/**
	 * This method return dashbased data based on passed frequency [Monthly, Weekly, Daily]
	 * @param fromCurrency
	 * @param toCurrency
	 * @param frequency
	 * @return
	 */
	@Override
	public Optional<DashboardData> findDashboardCurrencyExRate(String fromCurrency, String toCurrency,
			Frequency frequency) {
		LOGGER.info(Constant.ENTERED);
		String key = KeyGeneratorUtility.generateKey(toCurrency, fromCurrency);
		LOGGER.info("Key generated for request {}",key);
		return dashboardRepository.findFirstByKeyAndFrequency(key, frequency);
	}
}
