package de.andrena.testing.tools.image.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableConfigurationProperties
public class MongoDbConfiguration extends AbstractMongoConfiguration {
	
	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}

	@Override
	protected String getDatabaseName() {
		return "db";
	}
	
	@Override
	public Mongo mongo() throws Exception {
		MongoConfigProperties properties = mongoConfigProperties();
		
		return new MongoClient(properties.getIp());
	}
	
	@Bean
	@ConfigurationProperties(prefix="mongodb.service")
	public MongoConfigProperties mongoConfigProperties() {
		return new MongoConfigProperties();
	}
	
	public static class MongoConfigProperties {
		private String ip;

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}
	}

}