package de.andrena.testing.tools.weather.consumer.woeid;

import java.math.BigDecimal;

public interface WhereOnEarthIdService {

	String getWhereOnEarthId(BigDecimal longitude, BigDecimal latitude);

}