package de.andrena.testing.tools.weather.consumer.weather.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class WeatherChannelResults implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private WeatherChannel channel;

	public WeatherChannel getChannel() {
		return channel;
	}

	public void setChannel(WeatherChannel channel) {
		this.channel = channel;
	}

}
