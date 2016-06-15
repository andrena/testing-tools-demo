package de.andrena.testing.tools.weather.consumer.woeid;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import de.andrena.testing.tools.weather.consumer.categories.External;
import de.andrena.testing.tools.weather.consumer.woeid.api.WoeIdRequest;

@Category(External.class)
public class WhereOnEarthIdServiceImplIntegrationTest {
	
	private static final String WOE_ID_KARLSRUHE = "664942";
	
	private WhereOnEarthIdService classUnderTest;
	
	@Before
	public void setUp() {
		classUnderTest = new WhereOnEarthIdServiceImpl();
	}

	@Test
	public void testGetWhereOnEarthIdKarlsruhe() throws Exception {
		WoeIdRequest request = new WoeIdRequest("Karlsruhe", "Baden-Württemberg", "DE");

		String outcome = classUnderTest.getWhereOnEarthId(request);
		
		assertThat(outcome, is(WOE_ID_KARLSRUHE));
	}
	
	@Test
	public void testGetWhereOnEarthIdUnknown() throws Exception {
		WoeIdRequest request = new WoeIdRequest("", "", "");
		
		String outcome = classUnderTest.getWhereOnEarthId(request);
		
		assertThat(outcome, is("unknown"));
	}

}
