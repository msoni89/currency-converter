package com.practice.dto;

import com.google.gson.annotations.SerializedName;

public class RealtimeCurrencyExchangeRate {

	@SerializedName("Realtime Currency Exchange Rate")
	private RealtimeData realtimeData;

	@SerializedName("Realtime Currency Exchange Rate")
	public RealtimeData getRealtimeData() {
		return realtimeData;
	}

	@SerializedName("Realtime Currency Exchange Rate")
	public void setRealtimeData(RealtimeData realtimeData) {
		this.realtimeData = realtimeData;
	}

	@Override
	public String toString() {
		return "RealtimeData [data=" + realtimeData + "]";
	}

}
