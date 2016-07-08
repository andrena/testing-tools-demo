package de.andrena.testing.tools.weather.consumer.geolocation.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GeoLocation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private GeoPostalCode[] postalCodes;

	public GeoPostalCode[] getPostalCodes() {
		return postalCodes;
	}

	public void setPostalCodes(GeoPostalCode[] postalCodes) {
		this.postalCodes = postalCodes;
	}

}
