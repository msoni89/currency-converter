package com.practice.model;

import java.time.LocalDateTime;

import com.practice.enums.Interval;
import com.practice.enums.OutputSize;

public class MetaData {

	private String information;
	private String fromSymbol;
	private String toSymbol;
	private LocalDateTime lastRefreshed;
	private Interval interval;
	private OutputSize outputSize;
	private String timeZone;

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getFromSymbol() {
		return fromSymbol;
	}

	public void setFromSymbol(String fromSymbol) {
		this.fromSymbol = fromSymbol;
	}

	public String getToSymbol() {
		return toSymbol;
	}

	public void setToSymbol(String toSymbol) {
		this.toSymbol = toSymbol;
	}

	public LocalDateTime getLastRefreshed() {
		return lastRefreshed;
	}

	public void setLastRefreshed(LocalDateTime lastRefreshed) {
		this.lastRefreshed = lastRefreshed;
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	public OutputSize getOutputSize() {
		return outputSize;
	}

	public void setOutputSize(OutputSize outputSize) {
		this.outputSize = outputSize;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	@Override
	public String toString() {
		return "MetaData [_1Information=" + information + ", _2FromSymbol=" + fromSymbol + ", _3ToSymbol=" + toSymbol
				+ ", _4LastRefreshed=" + lastRefreshed + ", _5Interval=" + interval + ", _6OutputSize=" + outputSize
				+ ", _7TimeZone=" + timeZone + "]";
	}

}