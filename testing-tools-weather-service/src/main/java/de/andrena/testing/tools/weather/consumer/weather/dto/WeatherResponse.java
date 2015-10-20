package de.andrena.testing.tools.weather.consumer.weather.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class WeatherResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private WeatherResults query;

	public WeatherResults getQuery() {
		return query;
	}

	public void setQuery(WeatherResults query) {
		this.query = query;
	}
	
}