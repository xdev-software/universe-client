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

import org.eclipse.microprofile.config.ConfigProvider;


public class UniverseConfiguration
{
	public static final String PROPERTY_KEY_APPLICATION_ID = "universe.application.id";
	public static final String PROPERTY_KEY_APPLICATION_SECRET = "universe.application.secret";
	public static final String PROPERTY_KEY_AUTHORIZATION_CODE = "universe.authorization.code";
	public static final String PROPERTY_KEY_REDIRECT_URI = "universe.redirect.uri";
	public static final String PROPERTY_KEY_BEARER_TOKEN = "universe.bearer.token";
	
	private String applicationId;
	private String applicationSecret;
	private String authorizationCode;
	private String redirectUri;
	private String bearerToken;
	
	public UniverseConfiguration withApplicationId(final String applicationId)
	{
		this.applicationId = applicationId;
		return this;
	}
	
	public String getApplicationId()
	{
		if(this.applicationId == null)
		{
			this.applicationId = ConfigProvider.getConfig().getValue(PROPERTY_KEY_APPLICATION_ID, String.class);
			return this.applicationId;
		}
		return this.applicationId;
	}
	
	public UniverseConfiguration withApplicationSecret(final String applicationSecret)
	{
		this.applicationSecret = applicationSecret;
		return this;
	}
	
	public String getApplicationSecret()
	{
		if(this.applicationSecret == null)
		{
			this.applicationSecret = ConfigProvider.getConfig().getValue(
				PROPERTY_KEY_APPLICATION_SECRET,
				String.class);
			return this.applicationSecret;
		}
		return this.applicationSecret;
	}
	
	public UniverseConfiguration withAuthorizationCode(final String authorizationCode)
	{
		this.authorizationCode = authorizationCode;
		return this;
	}
	
	public String getAuthorizationCode()
	{
		if(this.authorizationCode == null)
		{
			this.authorizationCode = ConfigProvider.getConfig().getValue(
				PROPERTY_KEY_AUTHORIZATION_CODE,
				String.class);
			return this.authorizationCode;
		}
		return this.authorizationCode;
	}
	
	public UniverseConfiguration withRedirectUri(final String redirectUri)
	{
		this.redirectUri = redirectUri;
		return this;
	}
	
	/**
	 * Default value: {@code urn:ietf:wg:oauth:2.0:oob} which is the value for local calls.
	 */
	public String getRedirectUri()
	{
		if(this.redirectUri == null)
		{
			this.redirectUri = ConfigProvider.getConfig().getValue(PROPERTY_KEY_REDIRECT_URI, String.class);
			if(this.redirectUri == null)
			{
				return "urn:ietf:wg:oauth:2.0:oob";
			}
			return this.redirectUri;
		}
		return this.redirectUri;
	}
	
	public UniverseConfiguration withBearerToken(final String bearerToken)
	{
		this.bearerToken = bearerToken;
		return this;
	}
	
	public String getBearerToken()
	{
		if(this.bearerToken == null)
		{
			this.bearerToken = ConfigProvider.getConfig().getValue(PROPERTY_KEY_BEARER_TOKEN, String.class);
			return this.bearerToken;
		}
		return this.bearerToken;
	}
}
