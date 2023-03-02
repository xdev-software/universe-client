package software.xdev.universe.requests.get_events;

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
	"title",
	"description",
	"url"
})
public class Event
{
	
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
	@JsonProperty("id")
	private String id;
	@JsonProperty("title")
	private String title;
	@JsonProperty("description")
	private String description;
	@JsonProperty("url")
	private String url;
	
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
	
	@JsonProperty("title")
	public String getTitle()
	{
		return title;
	}
	
	@JsonProperty("title")
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	@JsonProperty("description")
	public String getDescription()
	{
		return description;
	}
	
	@JsonProperty("description")
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	@JsonProperty("url")
	public String getUrl()
	{
		return url;
	}
	
	@JsonProperty("url")
	public void setUrl(String url)
	{
		this.url = url;
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
