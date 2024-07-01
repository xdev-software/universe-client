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
	private final Map<String, Object> additionalProperties = new LinkedHashMap<>();
	@JsonProperty("id")
	private String id;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private Object lastName;
	
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
	
	@JsonProperty("firstName")
	public String getFirstName()
	{
		return this.firstName;
	}
	
	@JsonProperty("firstName")
	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}
	
	@JsonProperty("lastName")
	public Object getLastName()
	{
		return this.lastName;
	}
	
	@JsonProperty("lastName")
	public void setLastName(final Object lastName)
	{
		this.lastName = lastName;
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
