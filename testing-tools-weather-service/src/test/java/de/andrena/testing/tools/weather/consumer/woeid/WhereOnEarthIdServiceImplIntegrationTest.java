package de.andrena.testing.tools.weather.consumer.woeid;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import de.andrena.testing.tools.weather.consumer.categories.External;

@Category(External.class)
public class WhereOnEarthIdServiceImplIntegrationTest {
	
	private static final String WOE_ID_KARLSRUHE = "12836182";
	
	private WhereOnEarthIdService classUnderTest;
	
	@Before
	public void setUp() {
		classUnderTest = new WhereOnEarthIdServiceImpl();
	}

	@Test
	public void testGetWhereOnEarthId() throws Exception {
		BigDecimal longitudeKarlsruhe = new BigDecimal("8.40471");
		BigDecimal latitudeKarlsruhe = new BigDecimal("49.01464");

		String outcome = classUnderTest.getWhereOnEarthId(longitudeKarlsruhe, latitudeKarlsruhe);
		
		assertThat(outcome, is(WOE_ID_KARLSRUHE));
	}

}
