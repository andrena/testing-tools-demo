package de.andrena.testing.tools.clothing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties
public class ClothingRecommendationApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ClothingRecommendationApplication.class, args);
	}
	
	@Bean
	public ClothingConfigurationProperties clothingProperties() {
		return new ClothingConfigurationProperties();
	}
	
	@ConfigurationProperties(locations= {"classpath:application.yml"}, prefix="weather.service")
	public static class ClothingConfigurationProperties {
		private String ip;

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}
	}

}
