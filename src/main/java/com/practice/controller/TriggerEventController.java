package com.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.practice.constant.Constant;
import com.practice.dto.ResponseDto;
import com.practice.service.ITriggerService;

@RestController
@RequestMapping("/v1/trigger")
public class TriggerEventController {

	@Autowired
	private ITriggerService triggerService;

	/**
	 * This method is used for triggering/refreshing data into redis database. It send message into queue.
	 * @param fromCurrency
	 * @param toCurrency
	 * @return
	 */
	@GetMapping("/from/{from}/to/{to}")
	public ResponseEntity<ResponseDto> trigger(@PathVariable("from") String fromCurrency,
			@PathVariable("to") String toCurrency) {
		return triggerService.trigger(fromCurrency, toCurrency).map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, Constant.NOT_FOUND_ERROR_MESSAGE));
	}
}
