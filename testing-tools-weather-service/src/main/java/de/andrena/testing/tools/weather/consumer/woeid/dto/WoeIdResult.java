package de.andrena.testing.tools.weather.consumer.woeid.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class WoeIdResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("place")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private WoeId[] places;

	public WoeId[] getPlaces() {
		return places;
	}

	public void setPlaces(WoeId[] places) {
		this.places = places;
	}
	
}
