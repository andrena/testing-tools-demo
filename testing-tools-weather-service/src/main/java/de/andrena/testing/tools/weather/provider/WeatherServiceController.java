package de.andrena.testing.tools.weather.provider;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.andrena.testing.tools.weather.consumer.geolocation.GeoLocationService;
import de.andrena.testing.tools.weather.consumer.geolocation.dto.GeoPostalCode;
import de.andrena.testing.tools.weather.consumer.weather.WeatherByLocationService;
import de.andrena.testing.tools.weather.consumer.weather.dto.WeatherChannel;
import de.andrena.testing.tools.weather.consumer.weather.dto.WeatherCondition;
import de.andrena.testing.tools.weather.consumer.weather.dto.WeatherItem;
import de.andrena.testing.tools.weather.consumer.weather.dto.WeatherLocation;
import de.andrena.testing.tools.weather.consumer.woeid.WhereOnEarthIdService;
import de.andrena.testing.tools.weather.consumer.woeid.api.WoeIdRequest;
import de.andrena.testing.tools.weather.provider.dto.Weather;
import de.andrena.testing.tools.weather.provider.transform.ConsumerDtoToProviderDtoTransformer;

@RestController
public class WeatherServiceController {
	
	@Autowired
	private GeoLocationService geoLocationService;
	@Autowired
	private WhereOnEarthIdService whereOnEarthIdService;
	@Autowired
	private WeatherByLocationService weatherByLocationService;
	@Autowired
	private ConsumerDtoToProviderDtoTransformer dtoTransformer;
	
	@RequestMapping("/weather/{longitude}/{latitude}/")
	@ResponseBody
	public Weather getWeatherByCoordinates(@PathVariable("longitude")  BigDecimal longitude, @PathVariable("latitude")  BigDecimal latitude) {
		GeoPostalCode geoDetails = getGeoLocationBy(longitude, latitude);
		
		String whereOnEarthId = getWhereOnEarthId(new WoeIdRequest(geoDetails.getPlaceName(), geoDetails.getState(), geoDetails.getCountryCode()));
		
		return getWeatherByWoeId(whereOnEarthId);
	}
	
	private GeoPostalCode getGeoLocationBy(BigDecimal longitude,BigDecimal latitude) {
		return geoLocationService.getGeoLocationBy(longitude, latitude);
	}
	
	private String getWhereOnEarthId(WoeIdRequest woeIdRequest) {
		return whereOnEarthIdService.getWhereOnEarthId(woeIdRequest);
	}
	
	private Weather getWeatherByWoeId(String woeId) {
		WeatherChannel weatherByWoeId = weatherByLocationService.getWeatherByWoeId(woeId);
		return transformWeatherItem(weatherByWoeId);
	}

	private Weather transformWeatherItem(WeatherChannel weatherChannel) {
		WeatherItem weatherItem = weatherChannel.getItem();
		WeatherCondition condition = weatherItem.getCondition();
		WeatherLocation location = weatherChannel.getLocation();
		return dtoTransformer.transformToWeather(condition.getCode(), condition.getTemp(), location.getCity());
	}
	
}
