package com.practice.confriguration;

import com.practice.constant.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import com.practice.service.impl.RedisMessageSubscriber;

/**
 * Redis related configuration
 */
@Configuration
@EnableRedisRepositories
public class RedisConfiguration {

	@Bean
	public LettuceConnectionFactory redisConnectionFactory(ApplicationProperties redisProperties) {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
				redisProperties.getRedisHost(), redisProperties.getRedisPort());
		return new LettuceConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean
	public GenericJackson2JsonRedisSerializer valSerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
		template.setValueSerializer(valSerializer());
		template.setHashValueSerializer(valSerializer());
		return template;
	}

	@Bean
	RedisMessageListenerContainer redisContainer(LettuceConnectionFactory redisConnectionFactory) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConnectionFactory);
		container.addMessageListener(messageListener(), topic());
		return container;
	}

	@Bean
	RedisMessageSubscriber redisMessageSubscriber() {
		return new RedisMessageSubscriber();
	}

	@Bean
	MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter(redisMessageSubscriber());
	}

	@Bean
	ChannelTopic topic() {
		return new ChannelTopic(Constant.QUEUE);
	}
}
