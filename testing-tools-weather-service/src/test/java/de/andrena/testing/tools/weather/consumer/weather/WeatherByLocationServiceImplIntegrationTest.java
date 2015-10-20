package de.andrena.testing.tools.weather.consumer.weather;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import de.andrena.testing.tools.weather.consumer.categories.External;
import de.andrena.testing.tools.weather.consumer.weather.dto.WeatherChannel;
import de.andrena.testing.tools.weather.consumer.weather.dto.WeatherItem;

@Category(External.class)
public class WeatherByLocationServiceImplIntegrationTest {
	
	private static final String WOE_ID_KARLSRUHE = "12836182";

	private WeatherByLocationService classUnderTest;
	
	@Before
	public void setUp() {
		classUnderTest = new WeatherByLocationServiceImpl();
	}

	@Test
	public void testGetWeatherByWoeId() throws Exception {
		WeatherChannel weather = classUnderTest.getWeatherByWoeId(WOE_ID_KARLSRUHE);
		WeatherItem weatherItem = weather.getItem();
		
		assertThat(weatherItem.getCondition().getText(), notNullValue());
	}

}
