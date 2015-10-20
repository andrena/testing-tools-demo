package de.andrena.testing.tools.weather.consumer.woeid.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class WoeIdResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private WoeIdResults query;

	public WoeIdResults getQuery() {
		return query;
	}

	public void setQuery(WoeIdResults query) {
		this.query = query;
	}
	
}