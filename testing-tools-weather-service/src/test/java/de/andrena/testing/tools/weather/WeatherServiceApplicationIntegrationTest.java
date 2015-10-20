package de.andrena.testing.tools.weather;

import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.springframework.http.HttpStatus;

public class WeatherServiceApplicationIntegrationTest {

	@Test
	public void integrationTestGetValidWeatherResponse() {
		get("http://localhost:8181/weather/{longitude}/{latitude}/", 4.0, 49.0)
			.then()
				.statusCode(HttpStatus.OK.value())
				.assertThat()
					.content("temperatureSpec", equalTo("WARM"))
					.content("temperature", equalTo(30))
					.content("city", equalTo("Karlsruhe"));
	}

}
