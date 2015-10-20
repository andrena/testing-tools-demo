package de.andrena.testing.tools.weather.consumer.woeid.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class WoeIdResults implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private WoeIdResult results;

	public WoeIdResult getResults() {
		return results;
	}

	public void setResults(WoeIdResult results) {
		this.results = results;
	}

}
