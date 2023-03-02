package software.xdev.universe.requests.get_buyers;

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
	"orders"
})
public class Event
{
	
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
	@JsonProperty("orders")
	private Orders orders;
	
	@JsonProperty("orders")
	public Orders getOrders()
	{
		return orders;
	}
	
	@JsonProperty("orders")
	public void setOrders(Orders orders)
	{
		this.orders = orders;
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
