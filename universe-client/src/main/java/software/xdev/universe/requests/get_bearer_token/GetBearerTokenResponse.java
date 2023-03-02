package software.xdev.universe.requests.get_bearer_token;

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
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
	
	@JsonProperty("access_token")
	public String getAccessToken()
	{
		return accessToken;
	}
	
	@JsonProperty("access_token")
	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}
	
	@JsonProperty("token_type")
	public String getTokenType()
	{
		return tokenType;
	}
	
	@JsonProperty("token_type")
	public void setTokenType(String tokenType)
	{
		this.tokenType = tokenType;
	}
	
	@JsonProperty("expires_in")
	public Integer getExpiresIn()
	{
		return expiresIn;
	}
	
	@JsonProperty("expires_in")
	public void setExpiresIn(Integer expiresIn)
	{
		this.expiresIn = expiresIn;
	}
	
	@JsonProperty("refresh_token")
	public String getRefreshToken()
	{
		return refreshToken;
	}
	
	@JsonProperty("refresh_token")
	public void setRefreshToken(String refreshToken)
	{
		this.refreshToken = refreshToken;
	}
	
	@JsonProperty("scope")
	public String getScope()
	{
		return scope;
	}
	
	@JsonProperty("scope")
	public void setScope(String scope)
	{
		this.scope = scope;
	}
	
	@JsonProperty("created_at")
	public Integer getCreatedAt()
	{
		return createdAt;
	}
	
	@JsonProperty("created_at")
	public void setCreatedAt(Integer createdAt)
	{
		this.createdAt = createdAt;
	}
	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties()
	{
		return this.additionalProperties;
	}
	
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value)
	{
		this.additionalProperties.put(name, value);
	}
}
