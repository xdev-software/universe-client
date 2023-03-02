package software.xdev.universe.requests.get_buyers;

import software.xdev.universe.requests.UniverseRequest;


public class GetBuyersRequest implements UniverseRequest<GetBuyersResponse>
{
	@Override
	public Class<GetBuyersResponse> getResponseClass()
	{
		return GetBuyersResponse.class;
	}
	
	public final String getQuery(String eventId)
	{
		return "{ \"query\": \"{ event(id: \\\"" + eventId + "\\\") { orders { "
			+ "nodes(limit: 0) {"
			+ "        buyer {"
			+ "          id"
			+ "          name"
			+ "          firstName"
			+ "          lastName"
			+ "          email"
			+ "          businessEmail"
			+ "          businessAddress"
			+ "          businessPhoneNumber"
			+ "          isBusinessSeller"
			+ "          locale"
			+ "          description"
			+ "          avatarUrl"
			+ "          smallAvatarUrl"
			+ "        }"
			+ "      }"
			+ " } } }\"}";
	}
}


