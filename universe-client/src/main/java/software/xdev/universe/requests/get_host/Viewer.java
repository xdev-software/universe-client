package software.xdev.universe.requests.get_host;

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
	"id",
	"firstName",
	"lastName"
})
public class Viewer
{
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
	@JsonProperty("id")
	private String id;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private Object lastName;
	
	@JsonProperty("id")
	public String getId()
	{
		return id;
	}
	
	@JsonProperty("id")
	public void setId(String id)
	{
		this.id = id;
	}
	
	@JsonProperty("firstName")
	public String getFirstName()
	{
		return firstName;
	}
	
	@JsonProperty("firstName")
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	@JsonProperty("lastName")
	public Object getLastName()
	{
		return lastName;
	}
	
	@JsonProperty("lastName")
	public void setLastName(Object lastName)
	{
		this.lastName = lastName;
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
