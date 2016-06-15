package de.andrena.testing.tools.weather.consumer.woeid.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class WoeIdResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("query")
	private WoeIdResults queryResults;

	public WoeIdResults getQueryResults() {
		return queryResults;
	}

	public void setQueryResults(WoeIdResults query) {
		this.queryResults = query;
	}
	
}