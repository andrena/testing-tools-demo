package de.andrena.testing.tools.weather.consumer.woeid.api;

public class WoeIdRequest {
	
	private static final String SEPARATOR = ", ";

	private String city;
	private String state;
	private String countryCode;

	public WoeIdRequest(String city, String state, String countryCode) {
		this.city = city;
		this.state = state;
		this.countryCode = countryCode;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(city).append(SEPARATOR).append(countryCode).append(SEPARATOR).append(state).toString();
	}
	

}
