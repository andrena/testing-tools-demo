package de.andrena.testing.tools.image;

import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ImageServiceApp.class })
@WebIntegrationTest(randomPort = true)
public class ImageServiceAppIntegrationTest {

	private static final String URL_TEMPLATE = "http://127.0.0.1:%d/image/{weather}/";

	@Value("${local.server.port}")
	private int randomPortForTest;

	@Test
	public void testGetValidImageResponse() {
		get(String.format(URL_TEMPLATE, randomPortForTest), "NEUTRAL")
			.then()
				.statusCode(HttpStatus.OK.value())
				.assertThat()
					.content(notNullValue());
	}

}
