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
package software.xdev.universe.requests.get_bearer_token;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"access_token",
	"token_type",
	"expires_in",
	"refresh_token",
	"scope",
	"created_at"
})
public class GetBearerTokenResponse
{
	
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new LinkedHashMap<>();
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("expires_in")
	private Integer expiresIn;
	@JsonProperty("refresh_token")
	private String refreshToken;
	@JsonProperty("scope")
	private String scope;
	@JsonProperty("created_at")
	private Integer createdAt;
	
	@JsonProperty("access_token")
	public String getAccessToken()
	{
		return this.accessToken;
	}
	
	@JsonProperty("access_token")
	public void setAccessToken(final String accessToken)
	{
		this.accessToken = accessToken;
	}
	
	@JsonProperty("token_type")
	public String getTokenType()
	{
		return this.tokenType;
	}
	
	@JsonProperty("token_type")
	public void setTokenType(final String tokenType)
	{
		this.tokenType = tokenType;
	}
	
	/**
	 * @return number of seconds to expiration
	 */
	@JsonProperty("expires_in")
	public Integer getExpiresIn()
	{
		return this.expiresIn;
	}
	
	@JsonProperty("expires_in")
	public void setExpiresIn(final Integer expiresIn)
	{
		this.expiresIn = expiresIn;
	}
	
	public Duration getExpiresInAsDuration()
	{
		return Duration.ofSeconds(this.getExpiresIn());
	}
	
	@JsonProperty("refresh_token")
	public String getRefreshToken()
	{
		return this.refreshToken;
	}
	
	@JsonProperty("refresh_token")
	public void setRefreshToken(final String refreshToken)
	{
		this.refreshToken = refreshToken;
	}
	
	@JsonProperty("scope")
	public String getScope()
	{
		return this.scope;
	}
	
	@JsonProperty("scope")
	public void setScope(final String scope)
	{
		this.scope = scope;
	}
	
	@JsonProperty("created_at")
	public Integer getCreatedAt()
	{
		return this.createdAt;
	}
	
	@JsonProperty("created_at")
	public void setCreatedAt(final Integer createdAt)
	{
		this.createdAt = createdAt;
	}
	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties()
	{
		return this.additionalProperties;
	}
	
	@JsonAnySetter
	public void setAdditionalProperty(final String name, final Object value)
	{
		this.additionalProperties.put(name, value);
	}
}
