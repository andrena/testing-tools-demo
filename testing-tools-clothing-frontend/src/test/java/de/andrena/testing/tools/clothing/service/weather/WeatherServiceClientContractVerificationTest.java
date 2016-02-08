package de.andrena.testing.tools.clothing.service.weather;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import de.andrena.testing.tools.clothing.ClothingRecommendationApplication;
import de.andrena.testing.tools.clothing.ClothingRecommendationApplication.ClothingConfigurationProperties;
import de.andrena.testing.tools.clothing.service.dto.WeatherClientDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ClothingRecommendationApplication.class})
public class WeatherServiceClientContractVerificationTest {

	private static final String LATITUDE = "49.0";
	private static final String LONGITUDE = "4.0";
	private static final String WEATHER_SERVICE_PROVIDER = "weatherServiceProvider";
	private static final String WEATHER_SERVICE_PROVIDER_IP = "127.0.0.1";
	private static final int WEATHER_SERVICE_PROVIDER_PORT = 8181;
    
	@Rule
    public PactProviderRule pactMockServerProviderRule = new PactProviderRule(WEATHER_SERVICE_PROVIDER, WEATHER_SERVICE_PROVIDER_IP, WEATHER_SERVICE_PROVIDER_PORT, this);
	
	@Autowired
	private WeatherServiceClient classUnderTest;
	@Autowired
	private ClothingConfigurationProperties configProperties;
	
	@Before 
	public void setUp() {
		configProperties.setIp(WEATHER_SERVICE_PROVIDER_IP);
	}

    @Pact(provider=WEATHER_SERVICE_PROVIDER, consumer="weatherServiceClient")
    public PactFragment createFragment(PactDslWithProvider builder) {
        return builder.uponReceiving("a weather request")
                .path(String.format("/weather/%s/%s/", LONGITUDE, LATITUDE))
                .method("GET")
                .willRespondWith()
                .headers(headers())
                .status(200)
                .body("{\"temperatureSpec\":\"WARM\",\"temperature\":30,\"city\":\"Karlsruhe\"}").toFragment();
    }

	private Map<String, String> headers() {
		return Collections.singletonMap("Content-Type", "application/json;charset=UTF-8");
	}

    @Test
    @PactVerification(WEATHER_SERVICE_PROVIDER)
    public void testContract() {
        WeatherClientDto weather = classUnderTest.getWeather(new BigDecimal(LONGITUDE), new BigDecimal(LATITUDE));
        
        assertThat(weather.getCity(), is("Karlsruhe"));
        assertThat(weather.getTemperatureSpec(), is("WARM"));
        assertThat(weather.getTemperature(), is(30));
    }
    
}
