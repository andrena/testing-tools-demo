package de.andrena.testing.tools.weather.consumer.woeid;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.andrena.testing.tools.weather.configuration.ConfigConstants;
import de.andrena.testing.tools.weather.consumer.constants.ServiceConstants;
import de.andrena.testing.tools.weather.consumer.woeid.api.WoeIdRequest;
import de.andrena.testing.tools.weather.consumer.woeid.dto.WoeIdResponse;
import de.andrena.testing.tools.weather.consumer.woeid.dto.WoeIdResult;
import de.andrena.testing.tools.weather.consumer.woeid.dto.WoeIdResults;

@Service
@Profile(ConfigConstants.USE_PRODUCTION)
public class WhereOnEarthIdServiceImpl implements WhereOnEarthIdService {
	
	private static final String WOEID_UNAVAILBLE = "unknown";
	private static final String WOEID_QUERY = "select * from geo.places where text=\"{woeIdRequest}\" &format=json";

	@Override
	public String getWhereOnEarthId(WoeIdRequest woeIdRequest) {
		WoeIdResults result= new RestTemplate().getForObject(ServiceConstants.SERVICE_PROVIDER_URL + WOEID_QUERY , WoeIdResponse.class, woeIdRequest.toString()).getQueryResults();
		
		return extractWoeIdFromResponse(result);
	}

	private String extractWoeIdFromResponse(WoeIdResults query) {
		WoeIdResult woeIdResult = query.getResults();
		
		if(woeIdResult != null && woeIdResult.getPlaces().length > 0) {
			return woeIdResult.getPlaces()[0].getWoeid();
		}
		return WOEID_UNAVAILBLE;
	}

}
