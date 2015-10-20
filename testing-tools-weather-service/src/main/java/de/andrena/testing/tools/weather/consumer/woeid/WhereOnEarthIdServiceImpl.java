package de.andrena.testing.tools.weather.consumer.woeid;

import java.math.BigDecimal;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.andrena.testing.tools.weather.configuration.ConfigConstants;
import de.andrena.testing.tools.weather.consumer.constants.ServiceConstants;
import de.andrena.testing.tools.weather.consumer.woeid.dto.WoeIdResponse;
import de.andrena.testing.tools.weather.consumer.woeid.dto.WoeIdResults;

@Service
@Profile(ConfigConstants.USE_PRODUCTION)
public class WhereOnEarthIdServiceImpl implements WhereOnEarthIdService {
	
	private String WOEID_QUERY = "select * from geo.placefinder where text=\"{latitude}, {longitude}\" and gflags=\"R\"&format=json";

	@Override
	public String getWhereOnEarthId(BigDecimal longitude, BigDecimal latitude) {
		WoeIdResults result= new RestTemplate().getForObject(ServiceConstants.SERVICE_PROVIDER_URL + WOEID_QUERY , WoeIdResponse.class, latitude.toString() , longitude.toString()).getQuery();
		
		return extractWoeIdFromResponse(result);
	}

	private String extractWoeIdFromResponse(WoeIdResults query) {
		return query.getResults().getResult().getWoeid();
	}

}
