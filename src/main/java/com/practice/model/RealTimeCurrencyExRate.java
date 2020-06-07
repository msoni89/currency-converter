package com.practice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("currency")
public class RealTimeCurrencyExRate implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private UUID id;
	@Indexed
	private String key;
	private String fromCurrencyCode;
	private String fromCurrencyName;
	private String toCurrencyCode;
	private String toCurrencyName;
	private BigDecimal exchangeRate;
	private LocalDateTime lastRefreshed;
	private LocalDateTime fetchedTime;
	private String timeZone;
	private BigDecimal bidPrice;
	private BigDecimal askPrice;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFromCurrencyCode() {
		return fromCurrencyCode;
	}

	public void setFromCurrencyCode(String fromCurrencyCode) {
		this.fromCurrencyCode = fromCurrencyCode;
	}

	public String getFromCurrencyName() {
		return fromCurrencyName;
	}

	public void setFromCurrencyName(String fromCurrencyName) {
		this.fromCurrencyName = fromCurrencyName;
	}

	public String getToCurrencyCode() {
		return toCurrencyCode;
	}

	public void setToCurrencyCode(String toCurrencyCode) {
		this.toCurrencyCode = toCurrencyCode;
	}

	public String getToCurrencyName() {
		return toCurrencyName;
	}

	public void setToCurrencyName(String toCurrencyName) {
		this.toCurrencyName = toCurrencyName;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public LocalDateTime getLastRefreshed() {
		return lastRefreshed;
	}

	public void setLastRefreshed(LocalDateTime lastRefreshed) {
		this.lastRefreshed = lastRefreshed;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public BigDecimal getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(BigDecimal bidPrice) {
		this.bidPrice = bidPrice;
	}

	public BigDecimal getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(BigDecimal askPrice) {
		this.askPrice = askPrice;
	}

	public LocalDateTime getFetchedTime() {
		return fetchedTime;
	}

	public void setFetchedTime(LocalDateTime fetchedTime) {
		this.fetchedTime = fetchedTime;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "RealTimeCurrencyExRate [id=" + id + ", key=" + key + ", fromCurrencyCode=" + fromCurrencyCode
				+ ", fromCurrencyName=" + fromCurrencyName + ", toCurrencyCode=" + toCurrencyCode + ", toCurrencyName="
				+ toCurrencyName + ", exchangeRate=" + exchangeRate + ", lastRefreshed=" + lastRefreshed
				+ ", fetchedTime=" + fetchedTime + ", timeZone=" + timeZone + ", bidPrice=" + bidPrice + ", askPrice="
				+ askPrice + "]";
	}

}
