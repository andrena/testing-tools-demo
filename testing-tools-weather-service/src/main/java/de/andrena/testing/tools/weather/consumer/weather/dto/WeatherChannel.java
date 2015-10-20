package de.andrena.testing.tools.weather.consumer.weather.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class WeatherChannel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private WeatherItem item;
	private WeatherLocation location;

	public WeatherItem getItem() {
		return item;
	}

	public void setItem(WeatherItem item) {
		this.item = item;
	}

	public WeatherLocation getLocation() {
		return location;
	}

	public void setLocation(WeatherLocation location) {
		this.location = location;
	}
	
}
