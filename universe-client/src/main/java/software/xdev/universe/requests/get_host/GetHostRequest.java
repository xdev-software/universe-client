package software.xdev.universe.requests.get_host;

import software.xdev.universe.requests.UniverseRequest;


public class GetHostRequest implements UniverseRequest<GetHostResponse>
{
	@Override
	public Class<GetHostResponse> getResponseClass()
	{
		return GetHostResponse.class;
	}
	
	public final String getQuery()
	{
		return "{ \"query\": \"{"
			+ "  viewer {"
			+ "      id"
			+ "      firstName"
			+ "      lastName"
			+ "    }"
			+ "}\"}";
	}
}


