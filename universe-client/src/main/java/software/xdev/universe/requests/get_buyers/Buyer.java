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
	"id",
	"name",
	"firstName",
	"lastName",
	"email",
	"businessEmail",
	"businessAddress",
	"businessPhoneNumber",
	"isBusinessSeller",
	"locale",
	"description",
	"avatarUrl",
	"smallAvatarUrl"
})
public class Buyer
{
	
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new LinkedHashMap<>();
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("email")
	private String email;
	@JsonProperty("businessEmail")
	private Object businessEmail;
	@JsonProperty("businessAddress")
	private Object businessAddress;
	@JsonProperty("businessPhoneNumber")
	private Object businessPhoneNumber;
	@JsonProperty("isBusinessSeller")
	private Boolean isBusinessSeller;
	@JsonProperty("locale")
	private String locale;
	@JsonProperty("description")
	private Object description;
	@JsonProperty("avatarUrl")
	private Object avatarUrl;
	@JsonProperty("smallAvatarUrl")
	private Object smallAvatarUrl;
	
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
	
	@JsonProperty("name")
	public String getName()
	{
		return this.name;
	}
	
	@JsonProperty("name")
	public void setName(final String name)
	{
		this.name = name;
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
	public String getLastName()
	{
		return this.lastName;
	}
	
	@JsonProperty("lastName")
	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}
	
	@JsonProperty("email")
	public String getEmail()
	{
		return this.email;
	}
	
	@JsonProperty("email")
	public void setEmail(final String email)
	{
		this.email = email;
	}
	
	@JsonProperty("businessEmail")
	public Object getBusinessEmail()
	{
		return this.businessEmail;
	}
	
	@JsonProperty("businessEmail")
	public void setBusinessEmail(final Object businessEmail)
	{
		this.businessEmail = businessEmail;
	}
	
	@JsonProperty("businessAddress")
	public Object getBusinessAddress()
	{
		return this.businessAddress;
	}
	
	@JsonProperty("businessAddress")
	public void setBusinessAddress(final Object businessAddress)
	{
		this.businessAddress = businessAddress;
	}
	
	@JsonProperty("businessPhoneNumber")
	public Object getBusinessPhoneNumber()
	{
		return this.businessPhoneNumber;
	}
	
	@JsonProperty("businessPhoneNumber")
	public void setBusinessPhoneNumber(final Object businessPhoneNumber)
	{
		this.businessPhoneNumber = businessPhoneNumber;
	}
	
	@JsonProperty("isBusinessSeller")
	public Boolean getIsBusinessSeller()
	{
		return this.isBusinessSeller;
	}
	
	@JsonProperty("isBusinessSeller")
	public void setIsBusinessSeller(final Boolean isBusinessSeller)
	{
		this.isBusinessSeller = isBusinessSeller;
	}
	
	@JsonProperty("locale")
	public String getLocale()
	{
		return this.locale;
	}
	
	@JsonProperty("locale")
	public void setLocale(final String locale)
	{
		this.locale = locale;
	}
	
	@JsonProperty("description")
	public Object getDescription()
	{
		return this.description;
	}
	
	@JsonProperty("description")
	public void setDescription(final Object description)
	{
		this.description = description;
	}
	
	@JsonProperty("avatarUrl")
	public Object getAvatarUrl()
	{
		return this.avatarUrl;
	}
	
	@JsonProperty("avatarUrl")
	public void setAvatarUrl(final Object avatarUrl)
	{
		this.avatarUrl = avatarUrl;
	}
	
	@JsonProperty("smallAvatarUrl")
	public Object getSmallAvatarUrl()
	{
		return this.smallAvatarUrl;
	}
	
	@JsonProperty("smallAvatarUrl")
	public void setSmallAvatarUrl(final Object smallAvatarUrl)
	{
		this.smallAvatarUrl = smallAvatarUrl;
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
