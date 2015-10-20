package de.andrena.testing.tools.weather.provider.dto;

import java.io.Serializable;

public class Weather implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String temperatureSpec;
	private String city;
	private int temperature;

	public Weather(String temparaturSpec, int temperature, String city) {
		this.temperatureSpec = temparaturSpec;
		this.temperature = temperature;
		this.city = city;
	}

	public String getTemperatureSpec() {
		return temperatureSpec;
	}

	public void setTemperatureSpec(String temperatureSpec) {
		this.temperatureSpec = temperatureSpec;
	}

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

}
