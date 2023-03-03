package software.xdev.universe.requests.get_attendees;

import software.xdev.universe.requests.UniverseRequest;


public class GetAttendeesRequest implements UniverseRequest<GetAttendeesResponse>
{
	@Override
	public Class<GetAttendeesResponse> getResponseClass()
	{
		return GetAttendeesResponse.class;
	}
	
	public final String getQuery(String eventId, int limit, int offset)
	{
		return "{ \"query\": \"{ event(id: \\\"" + eventId + "\\\") {"
			+ "    attendees {"
			+ "      nodes(limit: " + limit + " offset: " + offset + ") {"
			+ "        id"
			+ "        firstName"
			+ "        lastName"
			+ "        email"
			+ "        rate {"
			+ "          name"
			+ "        }"
			+ "        order {"
			+ "          state"
			+ "          id"
			+ "        }"
			+ "        answers {"
			+ "          question {"
			+ "            id"
			+ "            question"
			+ "            type"
			+ "          }"
			+ "          value"
			+ "        }"
			+ "      }"
			+ "    }"
			+ "  }"
			+ " } \"}";
	}
}


