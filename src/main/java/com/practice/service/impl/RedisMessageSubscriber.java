package com.practice.service.impl;

import com.practice.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import com.google.gson.Gson;
import com.practice.dto.EventDto;
import com.practice.service.IRefreshDataService;

public class RedisMessageSubscriber implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessageSubscriber.class);


	@Autowired
	private IRefreshDataService dataService;

	@Autowired
	private Gson gson;
	
	@Autowired
	private GenericJackson2JsonRedisSerializer valSerializer;

	/**
	 * This method received events of redis queue.
	 * @param message
	 * @param pattern
	 */
	public void onMessage(Message message, byte[] pattern) {
		LOGGER.info(Constant.ENTERED);
		EventDto event = gson.fromJson(valSerializer.deserialize(message.getBody()).toString(), EventDto.class);
		LOGGER.info("Received Event {}", event);
		dataService.refresh(event.getFromCurrency(),event.getToCurrency());
		LOGGER.info("Data Refreshed");
	}
}