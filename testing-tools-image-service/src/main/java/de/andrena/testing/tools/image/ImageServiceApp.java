package de.andrena.testing.tools.image;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@SpringBootApplication
public class ImageServiceApp {
	
	public static void main(String[] args) {
		SpringApplication.run(ImageServiceApp.class, args);
	}
	
	@Component
	public static class Initializer {
		
		private static final String CONTENT_TYPE_PNG = "image/png";
		
		@Autowired
		private GridFsOperations gridFsOperations;
		
		@PostConstruct
		public void init() {
			try {
				cleanDatabase();
				persistWeatherImage("penguin-cold.png", "COLD");
				persistWeatherImage("penguin-neutral.png", "NEUTRAL");
				persistWeatherImage("penguin-warm.png", "WARM");
				persistWeatherImage("penguin-raincoat.png", "WET");
			} catch (IOException e) {
				throw new RuntimeException("Could not clean database and persist images: " + e);
			}
		}

		private void cleanDatabase() {
			Query imageDeleteQuery = new Query().addCriteria(Criteria.where("metadata.weather").exists(true));
			gridFsOperations.delete(imageDeleteQuery);
		}
		
		private void persistWeatherImage(String imageFile, String temperatureSpec) throws IOException {
			DBObject metaData = getMetaData(temperatureSpec);
			InputStream imageInputStream = loadImage(imageFile);
			persistImage(imageFile, metaData, imageInputStream);
		}
		
		private DBObject getMetaData(String temperatureSpec) {
			DBObject metaData = new BasicDBObject();
			metaData.put("weather", temperatureSpec);
			return metaData;
		}
		
		private InputStream loadImage(String imageFile) throws IOException {
			return new ClassPathResource(imageFile).getInputStream();
		}

		private void persistImage(String imageFile, DBObject metaData, InputStream imageInputStream) throws IOException {
			gridFsOperations.store(imageInputStream, imageFile, CONTENT_TYPE_PNG, metaData);
		}
	}
}
