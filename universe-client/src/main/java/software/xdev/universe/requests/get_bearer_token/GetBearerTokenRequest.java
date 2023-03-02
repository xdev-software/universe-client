package software.xdev.universe.requests.get_bearer_token;

import software.xdev.universe.requests.UniverseRequest;


public class GetBearerTokenRequest implements UniverseRequest<GetBearerTokenResponse>
{
	@Override
	public Class<GetBearerTokenResponse> getResponseClass()
	{
		return GetBearerTokenResponse.class;
	}
	
	public final String getQuery(
		final String applicationId,
		final String applicationSecret,
		final String authorizationCode,
		final String redirectUri)
	{
		return "{ \"grant_type\": \"authorization_code\", \"client_id\":\"" + applicationId + "\", \"client_secret"
			+ "\":\""
			+ applicationSecret + "\", \"redirect_uri\":\"" + redirectUri + "\", \"code\":\"" + authorizationCode
			+ "\"}";
	}
}


