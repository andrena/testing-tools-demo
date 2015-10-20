package de.andrena.testing.tools.weather.provider.transform;

import org.springframework.stereotype.Component;

import de.andrena.testing.tools.weather.provider.constants.ConditionCode;
import de.andrena.testing.tools.weather.provider.constants.TemperatureSpec;
import de.andrena.testing.tools.weather.provider.dto.Weather;

@Component
public class ConsumerDtoToProviderDtoTransformer {
	
	public Weather transformToWeather(int weatherCode, int temparature, String city) {
		TemperatureSpec temparatureSpec = transformCodeToTeparatureSpec(weatherCode);
		
		return new Weather(temparatureSpec.name(), temparature, city);
	}
	
	private TemperatureSpec transformCodeToTeparatureSpec(int code) {
		return ConditionCode.fromCode(code).getTemperatureSpec();
	}

}
