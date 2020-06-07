package com.practice.enums;

public enum Interval {
	_5Min("5min"), _60Min("60min"), DAILY("Daily");

	private String value;

	Interval(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
