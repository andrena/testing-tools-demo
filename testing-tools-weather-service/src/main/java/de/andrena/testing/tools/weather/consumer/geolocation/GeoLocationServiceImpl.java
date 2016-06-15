package de.andrena.testing.tools.weather.consumer.geolocation;

import java.math.BigDecimal;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.andrena.testing.tools.weather.configuration.ConfigConstants;
import de.andrena.testing.tools.weather.consumer.geolocation.dto.GeoLocation;
import de.andrena.testing.tools.weather.consumer.geolocation.dto.GeoPostalCode;

@Service
@Profile(ConfigConstants.USE_PRODUCTION)
public class GeoLocationServiceImpl implements GeoLocationService {

	private static final String GEO_SERVICE_URL_TEMPLATE = "http://api.geonames.org/findNearbyPostalCodesJSON?lat={latitude}&lng={longitude}&username=clothingrec";
	
	@Override
	public GeoPostalCode getGeoLocationBy(BigDecimal longitude, BigDecimal latitude) {
		GeoLocation geoLocation = new RestTemplate().getForObject(GEO_SERVICE_URL_TEMPLATE, GeoLocation.class, latitude, longitude);
		
		if(geoLocation != null && geoLocation.getPostalCodes().length > 0) {
			return geoLocation.getPostalCodes()[0];
		}
		return new GeoPostalCode();
	}

}
