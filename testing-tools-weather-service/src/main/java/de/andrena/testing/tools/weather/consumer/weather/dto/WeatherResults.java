package de.andrena.testing.tools.weather.consumer.weather.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class WeatherResults implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public WeatherChannelResults results;

	public WeatherChannelResults getResults() {
		return results;
	}

	public void setResults(WeatherChannelResults results) {
		this.results = results;
	}

}
