package com.practice.service.impl;

import java.util.Optional;

import com.practice.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.practice.dto.EventDto;
import com.practice.dto.ResponseDto;
import com.practice.service.IMessagePublisher;
import com.practice.service.ITriggerService;

@Service
public class TriggerServiceImpl implements ITriggerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TriggerServiceImpl.class);

	@Autowired
	private IMessagePublisher messagePublisher;

	@Autowired
	private Gson gson;

	/**
	 * This method publish message into redis queue.
	 * @param fromCurrency
	 * @param toCurrency
	 * @return
	 */
	@Override
	public Optional<ResponseDto> trigger(String fromCurrency, String toCurrency) {
		LOGGER.info(Constant.ENTERED);
		messagePublisher.publish(gson.toJson(new EventDto(fromCurrency, toCurrency)));
		LOGGER.info("Event Published for from currency {} to currency {}", fromCurrency, toCurrency);
		return Optional.of(new ResponseDto(Constant.SUCCESS, 200));
	}

}
