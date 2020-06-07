package com.practice.confriguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class 

ApplicationProperties {
	private int redisPort;
	private String redisHost;
	private String apiKey;
	private String apiUrl;

	public ApplicationProperties(@Value("${spring.redis.port}") int redisPort,
			@Value("${spring.redis.host}") String redisHost, @Value("${api_key}") String apiKey,
			@Value("${api_url}") String apiUrl) {
		this.redisPort = redisPort;
		this.redisHost = redisHost;
		this.apiKey = apiKey;
		this.apiUrl = apiUrl;
	}

	/**
	 * This method return redis port
	 * @return int
	 */
	public int getRedisPort() {
		return redisPort;
	}

	/**
	 * This method return redis host
	 * @return String
	 */
	public String getRedisHost() {
		return redisHost;
	}

	/**
	 * This method return api key
	 * @return String
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * This method return api url
	 * @return String
	 */
	public String getApiUrl() {
		return apiUrl;
	}

}