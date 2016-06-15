package de.andrena.testing.tools.weather.consumer.geolocation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import de.andrena.testing.tools.weather.consumer.categories.External;
import de.andrena.testing.tools.weather.consumer.geolocation.dto.GeoPostalCode;

@Category(External.class)
public class GeoLocationServiceImplIntegrationTest {
	
	private static final String DE = "DE";
	private static final String BAWUE = "Baden-Württemberg";
	private static final String KARLSRUHE = "Karlsruhe";

	private GeoLocationServiceImpl classUnderTest;
	
	@Before
	public void setUp() {
		classUnderTest = new GeoLocationServiceImpl();
	}

	@Test
	public void testGetGeoLocationBy() throws Exception {
		BigDecimal latitudeKarlsruhe = new BigDecimal("49.01464");
		BigDecimal longitudeKarlsruhe = new BigDecimal("8.40471");

		GeoPostalCode outcome = classUnderTest.getGeoLocationBy(longitudeKarlsruhe, latitudeKarlsruhe);
		
		assertGeoPostalCode(outcome);
	}

	private void assertGeoPostalCode(GeoPostalCode outcome) {
		assertThat(outcome.getPlaceName(), is(KARLSRUHE));
		assertThat(outcome.getCountryCode(), is(DE));
		assertThat(outcome.getState(), is(BAWUE));
	}

}
