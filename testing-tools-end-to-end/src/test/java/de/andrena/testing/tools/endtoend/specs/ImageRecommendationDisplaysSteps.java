package de.andrena.testing.tools.endtoend.specs;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.andrena.testing.tools.endtoend.pageobjects.RecommendationPage;

public class ImageRecommendationDisplaysSteps {
	
	private static final String INTEGRATIONTEST_PROPERTIES_FILE = "integrationtest.properties";
	private static final String APPLICATION_URL_PROPERTY = "application.url";
	private static final String SELENIUM_URL_PROPERTY = "selenium.url";
	
	private RemoteWebDriver webDriver;
	private String applicationUrl;
	private String seleniumUrl;
	private RecommendationPage recommendationPage;

	public ImageRecommendationDisplaysSteps() {
		try {
			Properties properties = readProperties();
			initApplicationUrls(properties);
		} catch (IOException e) {
			fail("Could not init application Urls");
		}
	}
	
	private Properties readProperties() throws IOException {
		Properties properties = new Properties();
		properties.load(Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(INTEGRATIONTEST_PROPERTIES_FILE));
		return properties;
	}

	private void initApplicationUrls(Properties properties) {
		applicationUrl = (String) properties.get(APPLICATION_URL_PROPERTY);
		seleniumUrl = (String) properties.get(SELENIUM_URL_PROPERTY);
	}
	
    @Given("^Ich habe ein Browserfenster geoeffnet$")
	public void initWebDriver() throws MalformedURLException {
		webDriver = new RemoteWebDriver(new URL(seleniumUrl), DesiredCapabilities.chrome());
	}
	
    @When("^ich die Url der Anwendung aufrufe$")
    public void navigateToApplicationUrl() {
		webDriver.get(applicationUrl);
		recommendationPage = new RecommendationPage(webDriver);
	}
	
    @Then("^soll ein Bild mit einem Kleidungsvorschlag angezeigt werden$")
	public void imageIsDisplayed() throws Exception {
		Thread.sleep(10000); //for display purpose
		
		assertThat(recommendationPage.imageIsNotLoadingImage(), is(true));
	}
	
}
