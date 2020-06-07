package com.practice.dto;

import java.io.Serializable;

public class EventDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fromCurrency;
	private String toCurrency;

	public EventDto() {
		super();
	}

	public EventDto(String fromCurrency, String toCurrency) {
		super();
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	@Override
	public String toString() {
		return "Event [fromCurrency=" + fromCurrency + ", toCurrency=" + toCurrency + "]";
	}

}
