package software.xdev.universe.requests.get_events;

import software.xdev.universe.requests.UniverseRequest;


public class GetEventsRequest implements UniverseRequest<GetEventsResponse>
{
	@Override
	public Class<GetEventsResponse> getResponseClass()
	{
		return GetEventsResponse.class;
	}
	
	public final String getQuery(final String hostId)
	{
		return "{ \"query\": \"{"
			+ "   host(id: \\\"" + hostId + "\\\") {"
			+ "      events {"
			+ "        nodes {"
			+ "          id"
			+ "          title"
			+ "          description"
			+ "          url"
			+ "        }"
			+ "    }"
			+ "  }"
			+ "}\"}";
	}
}


