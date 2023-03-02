package software.xdev.universe.requests;

import software.xdev.universe.dtos.get_buyers.ResponseGetBuyers;


public class GetBuyersRequest implements UniverseRequest<ResponseGetBuyers>
{
	@Override
	public Class<ResponseGetBuyers> getResponseClass()
	{
		return ResponseGetBuyers.class;
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
	
	// 		return "{ \"query\": \"{ event(id: \\\"" + eventId + "\\\") { orders { "
	// + "nodes(limit: \\\"0\\\") {"
	// + "        buyer {"
	// + "          id"
	// + "          name"
	// + "          firstName"
	// + "          lastName"
	// + "          email"
	// + "          businessEmail"
	// + "          businessAddress"
	// + "          businessPhoneNumber"
	// + "          isBusinessSeller"
	// + "          locale"
	// + "          description"
	// + "          avatarUrl"
	// + "          smallAvatarUrl"
	// + "        }"
	// + "      }"
	// + " } } }\"}";
}


