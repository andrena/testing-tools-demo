package de.andrena.testing.tools.clothing.frontend.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.andrena.testing.tools.clothing.service.dto.WeatherClientDto;
import de.andrena.testing.tools.clothing.service.weather.WeatherServiceClient;

@RestController
public class ClothingRecommendationController {
	
	@Autowired
	private WeatherServiceClient weatherServiceClient;
	
	@RequestMapping("/clothing/recommendation/{longitude}/{latitude}/")
	@ResponseBody
	public WeatherClientDto displayClothingRecommendation(@PathVariable("longitude") BigDecimal longitude, @PathVariable("latitude") BigDecimal latitude) {
		return weatherServiceClient.getWeather(longitude, latitude);
	}

}
