package com.practice.enums;

public enum OutputSize {
	COMPACT("compact"), FULL("full");

	private String value;

	OutputSize(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
