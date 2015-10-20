package de.andrena.testing.tools.clothing.service.dto;

import java.io.Serializable;

public class WeatherClientDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String temperatureSpec;
	private String city;
	private int temperature;


	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTemperatureSpec() {
		return temperatureSpec;
	}

	public void setTemperatureSpec(String temperatureSpec) {
		this.temperatureSpec = temperatureSpec;
	}

}
