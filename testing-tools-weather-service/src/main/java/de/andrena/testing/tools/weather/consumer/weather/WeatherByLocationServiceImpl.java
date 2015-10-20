package de.andrena.testing.tools.weather.consumer.weather;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.andrena.testing.tools.weather.configuration.ConfigConstants;
import de.andrena.testing.tools.weather.consumer.constants.ServiceConstants;
import de.andrena.testing.tools.weather.consumer.weather.dto.WeatherChannel;
import de.andrena.testing.tools.weather.consumer.weather.dto.WeatherResponse;

@Service
@Profile(ConfigConstants.USE_PRODUCTION)
public class WeatherByLocationServiceImpl implements WeatherByLocationService {
	
	private String WEATHER_QUERY = "select * from weather.forecast where woeid = {woeid} and u = 'c' &format=json";
	
	@Override
	public WeatherChannel getWeatherByWoeId(String woeId) {
		WeatherResponse response = new RestTemplate().getForObject(ServiceConstants.SERVICE_PROVIDER_URL + WEATHER_QUERY, WeatherResponse.class, woeId);

		return extractWeatherChannel(response); 
	}

	private WeatherChannel extractWeatherChannel(WeatherResponse response) {
		return response.getQuery().getResults().getChannel();
	}

}
