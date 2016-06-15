package de.andrena.testing.tools.weather.consumer.weather.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class WeatherResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("query")
	private WeatherResults queryResults;

	public WeatherResults getQueryResults() {
		return queryResults;
	}

	public void setQueryResults(WeatherResults query) {
		this.queryResults = query;
	}
	
}