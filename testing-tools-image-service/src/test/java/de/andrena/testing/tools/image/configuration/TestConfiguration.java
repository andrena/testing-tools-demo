package de.andrena.testing.tools.image.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import de.andrena.testing.tools.image.configuration.MongoDbConfiguration.MongoConfigProperties;
import de.andrena.testing.tools.image.service.ImageService;

@Configuration
@PropertySource("classpath:test.properties")
@ComponentScan(basePackageClasses=ImageService.class)
@Import(MongoDbConfiguration.class)
public class TestConfiguration {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public MongoConfigProperties mongoConfigProperties() {
		MongoConfigProperties mongoConfigProperties = new MongoConfigProperties();
		mongoConfigProperties.setIp(environment.getProperty("mongodb.service.ip"));
		return mongoConfigProperties;
	}

}
