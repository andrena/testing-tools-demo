package de.andrena.testing.tools.weather.consumer.geolocation;

import java.math.BigDecimal;

import de.andrena.testing.tools.weather.consumer.geolocation.dto.GeoPostalCode;

public interface GeoLocationService {

	GeoPostalCode getGeoLocationBy(BigDecimal longitude, BigDecimal latitude);
	
}
