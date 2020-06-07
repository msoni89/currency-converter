package com.practice.service.impl;

import com.practice.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.practice.service.IMessagePublisher;

@Service
public class RedisMessagePublisher implements IMessagePublisher {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessagePublisher.class);

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private ChannelTopic topic;

	/**
	 * This method is used for sending message into redis server.
	 * It receive Json formatted message and send it over network
	 * @param json
	 */
	public void publish(String json) {
		LOGGER.info(Constant.ENTERED);
		redisTemplate.convertAndSend(topic.getTopic(),json);
		LOGGER.info("Message Sent {}", json);
	}

}
