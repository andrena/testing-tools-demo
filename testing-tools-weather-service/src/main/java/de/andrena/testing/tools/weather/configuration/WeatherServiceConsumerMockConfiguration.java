package de.andrena.testing.tools.weather.configuration;

import java.math.BigDecimal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import de.andrena.testing.tools.weather.consumer.geolocation.GeoLocationService;
import de.andrena.testing.tools.weather.consumer.geolocation.dto.GeoPostalCode;
import de.andrena.testing.tools.weather.consumer.weather.WeatherByLocationService;
import de.andrena.testing.tools.weather.consumer.weather.dto.WeatherChannel;
import de.andrena.testing.tools.weather.consumer.weather.dto.WeatherCondition;
import de.andrena.testing.tools.weather.consumer.weather.dto.WeatherItem;
import de.andrena.testing.tools.weather.consumer.weather.dto.WeatherLocation;
import de.andrena.testing.tools.weather.consumer.woeid.WhereOnEarthIdService;
import de.andrena.testing.tools.weather.consumer.woeid.api.WoeIdRequest;
import de.andrena.testing.tools.weather.provider.constants.ConditionCode;

@Configuration
@Profile(ConfigConstants.USE_MOCK)
public class WeatherServiceConsumerMockConfiguration {
	
	private static final int MOCK_WEATHER_CODE = ConditionCode.HOT.getCode();
	private static final int MOCK_TEMPARATURE = 30;
	private static final String MOCK_WEATHER_DESCRIPTION = "sunny weather";
	private static final String MOCK_WEATHER_LOCATION = "Karlsruhe";
	private static final String MOCK_WOE_ID = "MOCK_WOE_ID";

	@Bean
	public WeatherByLocationService mockWeatherByLocationService() {
		return new WeatherByLocationService() {
			@Override
			public WeatherChannel getWeatherByWoeId(String woeId) {
				if(!MOCK_WOE_ID.equals(woeId)) {
					return null;
				}
				
				WeatherLocation weatherLocation = prepareWeatherLocation();
				WeatherCondition condition = prepareWeatherCondition();
				WeatherItem weatherItem = prepareWeatherItem(condition);
				
				return prepareWeatherChannel(weatherLocation, weatherItem);
			}

			private WeatherChannel prepareWeatherChannel(WeatherLocation weatherLocation, WeatherItem weatherItem) {
				WeatherChannel channel = new WeatherChannel();
				channel.setItem(weatherItem);
				channel.setLocation(weatherLocation);
				return channel;
			}

			private WeatherCondition prepareWeatherCondition() {
				WeatherCondition condition = new WeatherCondition();
				condition.setCode(MOCK_WEATHER_CODE);
				condition.setTemp(MOCK_TEMPARATURE);
				condition.setText(MOCK_WEATHER_DESCRIPTION);
				return condition;
			}

			private WeatherLocation prepareWeatherLocation() {
				WeatherLocation weatherLocation = new WeatherLocation();
				weatherLocation.setCity(MOCK_WEATHER_LOCATION);
				return weatherLocation;
			}

			private WeatherItem prepareWeatherItem(WeatherCondition condition) {
				WeatherItem weatherItem = new WeatherItem();
				weatherItem.setCondition(condition);
				return weatherItem;
			}
		};
		
	}
	
	@Bean
	public WhereOnEarthIdService mockWhereOnEarthIdService() {
		return new WhereOnEarthIdService() {
			@Override
			public String getWhereOnEarthId(WoeIdRequest request) {
				return MOCK_WOE_ID;
			}
		};
	}
	
	@Bean
	public GeoLocationService mockGeoNamesService() {
		return new GeoLocationService() {
			@Override
			public GeoPostalCode getGeoLocationBy(BigDecimal longitude, BigDecimal latitude) {
				return new GeoPostalCode();
			}
		};
	}
	
	
}
