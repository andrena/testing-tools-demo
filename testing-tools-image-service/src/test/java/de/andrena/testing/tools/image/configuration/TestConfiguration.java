package de.andrena.testing.tools.image.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import de.andrena.testing.tools.image.service.ImageService;

@Configuration
@ComponentScan(basePackageClasses = ImageService.class)
@Import(MongoDbConfiguration.class)
public class TestConfiguration {

}
