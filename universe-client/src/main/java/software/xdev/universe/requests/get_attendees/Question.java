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
	"id",
	"question",
	"type"
})
public class Question
{
	
	@JsonProperty("id")
	private String id;
	@JsonProperty("question")
	private String question;
	@JsonProperty("type")
	private String type;
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new LinkedHashMap<>();
	
	@JsonProperty("id")
	public String getId()
	{
		return this.id;
	}
	
	@JsonProperty("id")
	public void setId(final String id)
	{
		this.id = id;
	}
	
	@JsonProperty("question")
	public String getQuestion()
	{
		return this.question;
	}
	
	@JsonProperty("question")
	public void setQuestion(final String question)
	{
		this.question = question;
	}
	
	@JsonProperty("type")
	public String getType()
	{
		return this.type;
	}
	
	@JsonProperty("type")
	public void setType(final String type)
	{
		this.type = type;
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
