/*
 * Copyright © 2023 XDEV Software (https://xdev.software)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package software.xdev.universe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;

import software.xdev.universe.requests.UniverseRequest;
import software.xdev.universe.requests.get_bearer_token.GetBearerTokenRequest;
import software.xdev.universe.requests.get_bearer_token.GetBearerTokenResponse;
import software.xdev.universe.requests.get_buyers.Buyer;
import software.xdev.universe.requests.get_buyers.GetBuyersRequest;
import software.xdev.universe.requests.get_buyers.GetBuyersResponse;
import software.xdev.universe.requests.get_events.Event;
import software.xdev.universe.requests.get_events.GetEventsRequest;
import software.xdev.universe.requests.get_events.GetEventsResponse;
import software.xdev.universe.requests.get_host.GetHostRequest;
import software.xdev.universe.requests.get_host.GetHostResponse;


/**
 * Client to communicate with the sessionize API.
 */
public class UniverseClient implements HasLogger
{
	public static final String UNIVERSE_GRAPHQL_URL = "https://www.universe.com/graphql";
	public static final String UNIVERSE_OAUTH_TOKEN = "https://www.universe.com/oauth/token";
	public static final String UNIVERSE_OAUTH_AUTHORIZE_URL = "https://www.universe.com/oauth/authorize";
	private final ObjectMapper objectMapper = new ObjectMapper();
	private UniverseConfiguration config = new UniverseConfiguration();
	
	public UniverseClient withConfig(UniverseConfiguration config)
	{
		this.config = config;
		return this;
	}
	
	public UniverseConfiguration getConfig()
	{
		return this.config;
	}
	
	/**
	 * After authorization on the returned URL, the authorization code is displayed. We need this code to call
	 * {@link #requestBearerToken()}.
	 *
	 * @return a url to call to get the {@link UniverseConfiguration#getAuthorizationCode()}
	 */
	public String getUrlToGetAuthorizationCode()
	{
		return getUrlToGetAuthorizationCode(
			getConfig().getApplicationId(),
			getConfig().getRedirectUri()
		);
	}
	
	/**
	 * After authorization on the returned URL, the authorization code is displayed. We need this code to call
	 * {@link #requestBearerToken()}.
	 *
	 * @return a url to call to get the {@link UniverseConfiguration#getAuthorizationCode()}
	 */
	public String getUrlToGetAuthorizationCode(
		final String applicationId,
		final String redirectUri
	)
	{
		return UNIVERSE_OAUTH_AUTHORIZE_URL + "?"
			+ "response_type=code&"
			+ "scope=public&"
			+ "client_id=" + applicationId
			+ "&redirect_uri=" + redirectUri;
	}
	
	public String requestBearerToken() throws IOException
	{
		return requestBearerToken(
			getConfig().getApplicationId(),
			getConfig().getApplicationSecret(),
			getConfig().getAuthorizationCode(),
			getConfig().getRedirectUri()
		);
	}
	
	public String requestBearerToken(
		final String applicationId,
		final String applicationSecret,
		final String authorizationCode,
		final String redirectUri
	) throws IOException
	{
		final GetBearerTokenRequest getBearerTokenRequest = new GetBearerTokenRequest();
		final GetBearerTokenResponse responseGetBearerToken =
			sendRequestAndParseResponse(
				getBearerTokenRequest,
				sendPostMessage(
					UNIVERSE_OAUTH_TOKEN,
					getBearerTokenRequest.getQuery(
						applicationId,
						applicationSecret,
						authorizationCode,
						redirectUri
					)
				)
			);
		return responseGetBearerToken.getAccessToken();
	}
	
	public List<Buyer> requestBuyersInEvent(String eventId) throws IOException
	{
		final GetBuyersRequest getBuyersRequest = new GetBuyersRequest();
		final GetBuyersResponse responseGetBuyers =
			sendRequestAndParseResponse(
				getBuyersRequest,
				sendPostMessage(
					UNIVERSE_GRAPHQL_URL,
					getBuyersRequest.getQuery(eventId),
					getConfig().getBearerToken()
				)
			);
		return responseGetBuyers.getData()
			.getEvent()
			.getOrders()
			.getNodes()
			.stream()
			.map(node -> node.getBuyer())
			.collect(Collectors.toList());
	}
	
	public String requestHostId() throws IOException
	{
		final GetHostRequest getHostRequest = new GetHostRequest();
		final GetHostResponse getHostResponse =
			sendRequestAndParseResponse(
				getHostRequest,
				sendPostMessage(
					UNIVERSE_GRAPHQL_URL,
					getHostRequest.getQuery(),
					getConfig().getBearerToken()
				)
			);
		return getHostResponse.getData().getViewer().getId();
	}
	
	public List<Event> requestEvents(String hostId) throws IOException
	{
		final GetEventsRequest getEventsRequest = new GetEventsRequest();
		final GetEventsResponse getEventsResponse =
			sendRequestAndParseResponse(
				getEventsRequest,
				sendPostMessage(
					UNIVERSE_GRAPHQL_URL,
					getEventsRequest.getQuery(hostId),
					getConfig().getBearerToken()
				)
			);
		return getEventsResponse.getData().getHost().getEvents().getNodes();
	}
	
	private <T> T sendRequestAndParseResponse(
		UniverseRequest<T> request,
		HttpResponse httpResponse) throws IOException
	{
		final String responseAsString = sendRequest(httpResponse);
		return objectMapper.readValue(responseAsString, request.getResponseClass());
	}
	
	private String sendRequest(HttpResponse httpResponse) throws IOException
	{
		this.getLogger().debug("StatusCode: " + httpResponse.getStatusLine().getStatusCode());
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity()
			.getContent(), StandardCharsets.UTF_8)))
		{
			String responseString = reader.lines().parallel().collect(Collectors.joining("\n"));
			this.getLogger().debug("ResponseString: " + responseString);
			return responseString;
		}
	}
	
	private HttpResponse sendPostMessage(
		final String urlToCall,
		final String jsonData,
		final String bearerToken) throws IOException
	{
		HttpClient httpClient = HttpClients.custom()
			.setDefaultRequestConfig(RequestConfig.custom()
				.setCookieSpec(CookieSpecs.STANDARD).build())
			.build();
		HttpPost con2 = new HttpPost(urlToCall);
		con2.addHeader(
			"Authorization",
			"Bearer " + bearerToken);
		con2.addHeader("Content-Type", "application/json");
		con2.setEntity(new StringEntity(jsonData, ContentType.APPLICATION_JSON));
		
		this.getLogger().debug("Request: " + con2);
		return httpClient.execute(con2);
	}
	
	private HttpResponse sendPostMessage(
		final String urlToCall,
		final String jsonData) throws IOException
	{
		HttpClient httpClient = HttpClients.custom()
			.setDefaultRequestConfig(RequestConfig.custom()
				.setCookieSpec(CookieSpecs.STANDARD).build())
			.build();
		HttpPost con2 = new HttpPost(urlToCall);
		con2.addHeader("Content-Type", "application/json");
		con2.setEntity(new StringEntity(jsonData, ContentType.APPLICATION_JSON));
		
		this.getLogger().debug("Request: " + con2);
		return httpClient.execute(con2);
	}
}
