package com.practice.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.google.gson.annotations.SerializedName;
import com.practice.enums.Frequency;

@RedisHash("dashboard_data")
public class DashboardData implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private UUID id;
	@Indexed
	private String key;
	@Indexed
	private Frequency frequency;
	private MetaData metaData;
	private List<TimeSeries> timeSeries;

	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	public List<TimeSeries> getTimeSeries() {
		return timeSeries;
	}

	public void setTimeSeries(List<TimeSeries> timeSeries) {
		this.timeSeries = timeSeries;
	}

	@Override
	public String toString() {
		return "MetaData [metaData=" + metaData + ", timeSeries=" + timeSeries + "]";
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

}