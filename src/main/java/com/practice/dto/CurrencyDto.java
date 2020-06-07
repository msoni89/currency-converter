package com.practice.dto;

public class CurrencyDto {

	private String currencyName;
	private String currencyCode;
	private String currencySymbol;
	
	public CurrencyDto(String currencyName, String currencyCode, String currencySymbol) {
		super();
		this.currencyName = currencyName;
		this.currencyCode = currencyCode;
		this.currencySymbol = currencySymbol;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	@Override
	public String toString() {
		return "Currency [currencyName=" + currencyName + ", currencyCode=" + currencyCode + ", currencySymbol="
				+ currencySymbol + "]";
	}

}
