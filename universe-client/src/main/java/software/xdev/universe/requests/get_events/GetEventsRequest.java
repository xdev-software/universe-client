package software.xdev.universe.requests.get_events;

import software.xdev.universe.requests.UniverseRequest;


public class GetEventsRequest implements UniverseRequest<GetEventsResponse>
{
	@Override
	public Class<GetEventsResponse> getResponseClass()
	{
		return GetEventsResponse.class;
	}
	
	public final String getQuery(final String hostId, int limit)
	{
		return "{ \"query\": \"{"
			+ "   host(id: \\\"" + hostId + "\\\") {"
			+ "      events {"
			+ "        nodes(limit: " + limit + ") {"
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


