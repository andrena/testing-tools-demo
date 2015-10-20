package de.andrena.testing.tools.weather.consumer.weather;

import de.andrena.testing.tools.weather.consumer.weather.dto.WeatherChannel;

public interface WeatherByLocationService {
	
	WeatherChannel getWeatherByWoeId(String woeId);

}
