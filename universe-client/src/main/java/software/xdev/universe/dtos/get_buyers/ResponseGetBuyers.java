package software.xdev.universe.dtos.get_buyers;

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
	"data"
})
public class ResponseGetBuyers
{
	
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
	@JsonProperty("data")
	private Data data;
	
	@JsonProperty("data")
	public Data getData()
	{
		return data;
	}
	
	@JsonProperty("data")
	public void setData(Data data)
	{
		this.data = data;
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
