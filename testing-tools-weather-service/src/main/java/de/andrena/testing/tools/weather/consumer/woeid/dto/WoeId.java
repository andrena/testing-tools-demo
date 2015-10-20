package de.andrena.testing.tools.weather.consumer.woeid.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WoeId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String woeid;

	public String getWoeid() {
		return woeid;
	}

	public void setWoeId(String woeid) {
		this.woeid = woeid;
	}
}
