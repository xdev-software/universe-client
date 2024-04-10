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
import java.io.UncheckedIOException;
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
import software.xdev.universe.requests.get_attendees.Attendee;
import software.xdev.universe.requests.get_attendees.GetAttendeesRequest;
import software.xdev.universe.requests.get_attendees.GetAttendeesResponse;
import software.xdev.universe.requests.get_bearer_token.GetBearerTokenRequest;
import software.xdev.universe.requests.get_bearer_token.GetBearerTokenResponse;
import software.xdev.universe.requests.get_buyers.Buyer;
import software.xdev.universe.requests.get_buyers.GetBuyersRequest;
import software.xdev.universe.requests.get_buyers.GetBuyersResponse;
import software.xdev.universe.requests.get_buyers.Node;
import software.xdev.universe.requests.get_events.Event;
import software.xdev.universe.requests.get_events.GetEventsRequest;
import software.xdev.universe.requests.get_events.GetEventsResponse;
import software.xdev.universe.requests.get_host.GetHostRequest;
import software.xdev.universe.requests.get_host.GetHostResponse;


/**
 * Client to communicate with the universe API.
 */
public class UniverseClient
{
	public static final String UNIVERSE_GRAPHQL_URL = "https://www.universe.com/graphql";
	public static final String UNIVERSE_OAUTH_TOKEN = "https://www.universe.com/oauth/token";
	public static final String UNIVERSE_OAUTH_AUTHORIZE_URL = "https://www.universe.com/oauth/authorize";
	
	protected final ObjectMapper objectMapper = new ObjectMapper();
	
	protected String applicationId;
	protected String applicationSecret;
	protected String redirectUri;
	
	protected String authorizationCode;
	
	protected String bearerToken;
	
	public UniverseClient(final UniverseConfiguration config)
	{
		this.applicationId = config.applicationId();
		this.applicationSecret = config.applicationSecret();
		this.redirectUri = config.redirectUri();
		this.authorizationCode = config.authorizationCode();
		this.bearerToken = config.bearerToken();
	}
	
	public UniverseClient withAuthorizationCode(final String authorizationCode)
	{
		this.authorizationCode = authorizationCode;
		return this;
	}
	
	public UniverseClient withBearerToken(final String bearerToken)
	{
		this.bearerToken = bearerToken;
		return this;
	}
	
	/**
	 * After authorization on the returned URL, the authorization code is displayed. We need this code to call
	 * {@link #requestBearerToken()}.
	 *
	 * @return a url to call to get the {@link UniverseConfiguration#getAuthorizationCode()}
	 */
	public String getUrlToGetAuthorizationCode()
	{
		return this.getUrlToGetAuthorizationCode(
			this.applicationId,
			this.redirectUri
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
		final String redirectUri)
	{
		return UNIVERSE_OAUTH_AUTHORIZE_URL
			+ "?response_type=code"
			+ "&scope=public"
			+ "&client_id=" + applicationId
			+ "&redirect_uri=" + redirectUri;
	}
	
	public GetBearerTokenResponse requestBearerToken()
	{
		return this.requestBearerToken(
			this.applicationId,
			this.applicationSecret,
			this.authorizationCode,
			this.redirectUri
		);
	}
	
	public GetBearerTokenResponse requestBearerToken(
		final String applicationId,
		final String applicationSecret,
		final String authorizationCode,
		final String redirectUri)
	{
		final GetBearerTokenRequest getBearerTokenRequest = new GetBearerTokenRequest();
		return this.sendRequestAndParseResponse(
				getBearerTokenRequest,
			this.sendPostMessage(
					UNIVERSE_OAUTH_TOKEN,
					getBearerTokenRequest.getQuery(
						applicationId,
						applicationSecret,
						authorizationCode,
						redirectUri
					)
				)
			);
	}
	
	/**
	 * @param eventId to get the events from
	 */
	public List<Buyer> requestBuyersInEvent(final String eventId)
	{
		return this.requestBuyersInEvent(eventId, 0, 0);
	}
	
	/**
	 * @param eventId to get the events from
	 * @param limit   0 is unlimited, 50 is max amount. Default is 0
	 * @param offset  Default is 0
	 */
	public List<Buyer> requestBuyersInEvent(final String eventId, final int limit, final int offset)
	{
		final GetBuyersRequest getBuyersRequest = new GetBuyersRequest();
		final GetBuyersResponse responseGetBuyers =
			this.sendRequestAndParseResponse(
				getBuyersRequest,
				this.sendPostMessage(
					UNIVERSE_GRAPHQL_URL,
					getBuyersRequest.getQuery(eventId, limit, offset),
					this.bearerToken
				)
			);
		return responseGetBuyers.getData()
			.getEvent()
			.getOrders()
			.getNodes()
			.stream()
			.map(Node::getBuyer)
			.collect(Collectors.toList());
	}
	
	/**
	 * @param eventId to get the events from
	 */
	public List<Attendee> requestAttendeesInEvent(final String eventId)
	{
		return this.requestAttendeesInEvent(eventId, 0, 0);
	}
	
	/**
	 * @param eventId to get the events from
	 * @param limit   0 is unlimited, 50 is max amount. Default is 0
	 * @param offset  Default is 0
	 */
	public List<Attendee> requestAttendeesInEvent(final String eventId, final int limit, final int offset)
	{
		final GetAttendeesRequest getBuyersRequest = new GetAttendeesRequest();
		final GetAttendeesResponse responseGetBuyers =
			this.sendRequestAndParseResponse(
				getBuyersRequest,
				this.sendPostMessage(
					UNIVERSE_GRAPHQL_URL,
					getBuyersRequest.getQuery(eventId, limit, offset),
					this.bearerToken
				)
			);
		return responseGetBuyers.getData()
			.getEvent()
			.getAttendees().getNodes();
	}
	
	public String requestHostId()
	{
		final GetHostRequest getHostRequest = new GetHostRequest();
		final GetHostResponse getHostResponse =
			this.sendRequestAndParseResponse(
				getHostRequest,
				this.sendPostMessage(
					UNIVERSE_GRAPHQL_URL,
					getHostRequest.getQuery(),
					this.bearerToken
				)
			);
		return getHostResponse.getData().getViewer().getId();
	}
	
	public List<Event> requestEvents(final String hostId)
	{
		final GetEventsRequest getEventsRequest = new GetEventsRequest();
		final GetEventsResponse getEventsResponse =
			this.sendRequestAndParseResponse(
				getEventsRequest,
				this.sendPostMessage(
					UNIVERSE_GRAPHQL_URL,
					getEventsRequest.getQuery(hostId, 0),
					this.bearerToken
				)
			);
		return getEventsResponse.getData().getHost().getEvents().getNodes();
	}
	
	private <T> T sendRequestAndParseResponse(
		final UniverseRequest<T> request,
		final HttpResponse httpResponse)
	{
		try
		{
			final String responseAsString = this.sendRequest(httpResponse);
			return this.objectMapper.readValue(responseAsString, request.getResponseClass());
		}
		catch(final IOException ioe)
		{
			throw new UncheckedIOException(ioe);
		}
	}
	
	private String sendRequest(final HttpResponse httpResponse) throws IOException
	{
		try(final BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity()
			.getContent(), StandardCharsets.UTF_8)))
		{
			return reader.lines().collect(Collectors.joining("\n"));
		}
	}
	
	private HttpResponse sendPostMessage(
		final String urlToCall,
		final String jsonData,
		final String bearerToken)
	{
		final HttpClient httpClient = HttpClients.custom()
			.setDefaultRequestConfig(RequestConfig.custom()
				.setCookieSpec(CookieSpecs.STANDARD).build())
			.build();
		final HttpPost post = new HttpPost(urlToCall);
		post.addHeader("Authorization", "Bearer " + bearerToken);
		post.addHeader("Content-Type", "application/json");
		post.setEntity(new StringEntity(jsonData, ContentType.APPLICATION_JSON));
		
		try
		{
			return httpClient.execute(post);
		}
		catch(final IOException ioe)
		{
			throw new UncheckedIOException(ioe);
		}
	}
	
	private HttpResponse sendPostMessage(
		final String urlToCall,
		final String jsonData)
	{
		final HttpClient httpClient = HttpClients.custom()
			.setDefaultRequestConfig(RequestConfig.custom()
				.setCookieSpec(CookieSpecs.STANDARD).build())
			.build();
		final HttpPost post = new HttpPost(urlToCall);
		post.addHeader("Content-Type", "application/json");
		post.setEntity(new StringEntity(jsonData, ContentType.APPLICATION_JSON));
		
		try
		{
			return httpClient.execute(post);
		}
		catch(final IOException e)
		{
			throw new UncheckedIOException(e);
		}
	}
}
