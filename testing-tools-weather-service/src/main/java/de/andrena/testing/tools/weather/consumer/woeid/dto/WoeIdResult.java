package de.andrena.testing.tools.weather.consumer.woeid.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class WoeIdResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Result")
	private WoeId result;

	public WoeId getResult() {
		return result;
	}

	public void setResult(WoeId result) {
		this.result = result;
	}
	
}
