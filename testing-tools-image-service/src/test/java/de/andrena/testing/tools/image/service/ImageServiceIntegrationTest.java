package de.andrena.testing.tools.image.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

import de.andrena.testing.tools.image.configuration.TestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class ImageServiceIntegrationTest {
	
	private static final String TEST_WEATHER_SPEC = "TESTWEATHER";
	private static final String TEST_IMAGE = "weather.png";
	private static final long TEST_IMAGE_SIZE = 5310L;
	
	@Autowired
	private GridFsOperations gridFsOperations;
	@Autowired
	private ImageService classUnderTest;
	
	@Before
	public void prepareMongoDatabase() throws Exception {
		cleanDatabase();
		populateDatabase();
	}
	
	private void cleanDatabase() {
		Query imageDeleteQuery = new Query().addCriteria(Criteria.where("metadata.weather").exists(true));
		gridFsOperations.delete(imageDeleteQuery);
	}

	private void populateDatabase() throws IOException {
		DBObject metaData = new BasicDBObject();
		metaData.put("weather", TEST_WEATHER_SPEC);

		InputStream imageInputStream = new ClassPathResource(TEST_IMAGE).getInputStream();
		gridFsOperations.store(imageInputStream, TEST_IMAGE, "image/png", metaData);
	}
	
	@Test
	public void testGetClothingImageForNoImageFound() throws Exception {
		GridFSDBFile outcome = classUnderTest.getClothingImageFor("UNKNOWN");
		
		assertThat(outcome, nullValue());
	}
	
	@Test
	public void testGetClothingImageForOneImageFound() throws Exception {
		GridFSDBFile outcome = classUnderTest.getClothingImageFor(TEST_WEATHER_SPEC);

		assertThat(outcome, notNullValue());
		assertThat(outcome.getFilename(), is(TEST_IMAGE));
		assertThat(outcome.getLength(), is(TEST_IMAGE_SIZE));
	}

}
