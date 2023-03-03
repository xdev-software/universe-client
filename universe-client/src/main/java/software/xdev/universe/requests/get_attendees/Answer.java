package software.xdev.universe.requests.get_attendees;

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
	"question",
	"value"
})
public class Answer
{
	
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
	@JsonProperty("question")
	private Question question;
	@JsonProperty("value")
	private String value;
	
	@JsonProperty("question")
	public Question getQuestion()
	{
		return question;
	}
	
	@JsonProperty("question")
	public void setQuestion(Question question)
	{
		this.question = question;
	}
	
	@JsonProperty("value")
	public String getValue()
	{
		return value;
	}
	
	@JsonProperty("value")
	public void setValue(String value)
	{
		this.value = value;
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
