package de.andrena.testing.tools.weather.consumer.woeid;

import de.andrena.testing.tools.weather.consumer.woeid.api.WoeIdRequest;

public interface WhereOnEarthIdService {

	String getWhereOnEarthId(WoeIdRequest woeIdRequest);

}