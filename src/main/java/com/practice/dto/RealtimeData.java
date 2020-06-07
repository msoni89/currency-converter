package com.practice.dto;

import com.google.gson.annotations.SerializedName;

public class RealtimeData {

	@SerializedName("1. From_Currency Code")
	private String fromCurrencyCode;
	@SerializedName("2. From_Currency Name")
	private String fromCurrencyName;
	@SerializedName("3. To_Currency Code")
	private String toCurrencyCode;
	@SerializedName("4. To_Currency Name")
	private String toCurrencyName;
	@SerializedName("5. Exchange Rate")
	private String exchangeRate;
	@SerializedName("6. Last Refreshed")
	private String lastRefreshed;
	@SerializedName("7. Time Zone")
	private String timeZone;
	@SerializedName("8. Bid Price")
	private String bidPrice;
	@SerializedName("9. Ask Price")
	private String askPrice;

	@SerializedName("1. From_Currency Code")
	public String getFromCurrencyCode() {
		return fromCurrencyCode;
	}

	@SerializedName("1. From_Currency Code")
	public void setFromCurrencyCode(String FromCurrencyCode) {
		this.fromCurrencyCode = FromCurrencyCode;
	}

	@SerializedName("2. From_Currency Name")
	public String getFromCurrencyName() {
		return fromCurrencyName;
	}

	@SerializedName("2. From_Currency Name")
	public void setFromCurrencyName(String fromCurrencyName) {
		this.fromCurrencyName = fromCurrencyName;
	}

	@SerializedName("3. To_Currency Code")
	public String getToCurrencyCode() {
		return toCurrencyCode;
	}

	@SerializedName("3. To_Currency Code")
	public void setToCurrencyCode(String toCurrencyCode) {
		this.toCurrencyCode = toCurrencyCode;
	}

	@SerializedName("4. To_Currency Name")
	public String getToCurrencyName() {
		return toCurrencyName;
	}

	@SerializedName("4. To_Currency Name")
	public void setToCurrencyName(String toCurrencyName) {
		this.toCurrencyName = toCurrencyName;
	}

	@SerializedName("5. Exchange Rate")
	public String getExchangeRate() {
		return exchangeRate;
	}

	@SerializedName("5. Exchange Rate")
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@SerializedName("6. Last Refreshed")
	public String getLastRefreshed() {
		return lastRefreshed;
	}

	@SerializedName("6. Last Refreshed")
	public void setLastRefreshed(String lastRefreshed) {
		this.lastRefreshed = lastRefreshed;
	}

	@SerializedName("7. Time Zone")
	public String getTimeZone() {
		return timeZone;
	}

	@SerializedName("7. Time Zone")
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	@SerializedName("8. Bid Price")
	public String getBidPrice() {
		return bidPrice;
	}

	@SerializedName("8. Bid Price")
	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}

	@SerializedName("9. Ask Price")
	public String getAskPrice() {
		return askPrice;
	}

	@SerializedName("9. Ask Price")
	public void setAskPrice(String askPrice) {
		this.askPrice = askPrice;
	}

	@Override
	public String toString() {
		return "Data [FromCurrencyCode=" + fromCurrencyCode + ", FromCurrencyName=" + fromCurrencyName
				+ ", ToCurrencyCode=" + toCurrencyCode + ", ToCurrencyName=" + toCurrencyName + ", ExchangeRate="
				+ exchangeRate + ", LastRefreshed=" + lastRefreshed + ", TimeZone=" + timeZone + ", BidPrice="
				+ bidPrice + ", AskPrice=" + askPrice + "]";
	}

}