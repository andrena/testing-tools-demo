package de.andrena.testing.tools.weather.consumer.weather.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private WeatherCondition condition;

	public WeatherCondition getCondition() {
		return condition;
	}

	public void setCondition(WeatherCondition condition) {
		this.condition = condition;
	}

}
