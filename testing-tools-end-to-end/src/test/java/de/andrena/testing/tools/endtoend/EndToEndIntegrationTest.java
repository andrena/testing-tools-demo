package de.andrena.testing.tools.endtoend;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import de.andrena.testing.tools.endtoend.pageobjects.RecommendationPage;

public class EndToEndIntegrationTest {

	private static final String INTEGRATIONTEST_PROPERTIES_FILE = "/integrationtest.properties";
	private static final String APPLICATION_URL_PROPERTY = "application.url";
	private static final String SELENIUM_URL_PROPERTY = "selenium.url";
	
	private RemoteWebDriver webDriver;

	@Before
	public void setup() throws Exception {
		Properties properties = readProperties();
		
		String applicationUrl = (String) properties.get(APPLICATION_URL_PROPERTY);
		String seleniumUrl = (String) properties.get(SELENIUM_URL_PROPERTY);
		
		initWebDriverAndNavigateToApplicationPage(applicationUrl, seleniumUrl);
	}

	private Properties readProperties() throws IOException {
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(INTEGRATIONTEST_PROPERTIES_FILE));
		return properties;
	}
	
	private void initWebDriverAndNavigateToApplicationPage(String applicationUrl, String seleniumUrl) throws MalformedURLException {
		initWebDriver(seleniumUrl);
		navigateToApplicationPage(applicationUrl);
	}

	private void initWebDriver(String seleniumUrl) throws MalformedURLException {
		webDriver = new RemoteWebDriver(new URL(seleniumUrl), DesiredCapabilities.chrome());
	}
	
	private void navigateToApplicationPage(String applicationUrl) {
		webDriver.get(applicationUrl);
	}
	
	@Test
	public void testDisplayedImage() throws Exception {
		RecommendationPage recommendationPage = new RecommendationPage(webDriver);
		
		Thread.sleep(10000); //for display purpose
		
		assertThat(recommendationPage.imageIsNotLoadingImage(), is(true));
	}
	
}
