package de.andrena.testing.tools.clothing.service.weather;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.andrena.testing.tools.clothing.ClothingRecommendationApplication.ClothingConfigurationProperties;
import de.andrena.testing.tools.clothing.service.dto.WeatherClientDto;

@Service
public class WeatherServiceClient {
	
	private static final String WEATHER_SERVICE_URL_TEMPLATE = "http://%s:8181/weather/{longitude}/{latitude}/";
	
	@Autowired
	private ClothingConfigurationProperties configurationProperties;
	
	public WeatherClientDto getWeather(BigDecimal longitude, BigDecimal latitude) {
		return new RestTemplate().getForObject(String.format(WEATHER_SERVICE_URL_TEMPLATE, configurationProperties.getIp()), WeatherClientDto.class, longitude, latitude);
	}

}
