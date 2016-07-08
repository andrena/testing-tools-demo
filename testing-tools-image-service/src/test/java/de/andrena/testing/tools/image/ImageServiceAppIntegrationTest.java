package de.andrena.testing.tools.image;

import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ImageServiceApp.class }, webEnvironment=WebEnvironment.RANDOM_PORT)
public class ImageServiceAppIntegrationTest {

	private static final String URL_TEMPLATE = "http://127.0.0.1:%d/image/{weather}/";

	@LocalServerPort
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
