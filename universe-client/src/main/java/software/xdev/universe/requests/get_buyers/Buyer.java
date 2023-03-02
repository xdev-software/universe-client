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
	private final Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
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
		return id;
	}
	
	@JsonProperty("id")
	public void setId(String id)
	{
		this.id = id;
	}
	
	@JsonProperty("name")
	public String getName()
	{
		return name;
	}
	
	@JsonProperty("name")
	public void setName(String name)
	{
		this.name = name;
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
	public String getLastName()
	{
		return lastName;
	}
	
	@JsonProperty("lastName")
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	@JsonProperty("email")
	public String getEmail()
	{
		return email;
	}
	
	@JsonProperty("email")
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	@JsonProperty("businessEmail")
	public Object getBusinessEmail()
	{
		return businessEmail;
	}
	
	@JsonProperty("businessEmail")
	public void setBusinessEmail(Object businessEmail)
	{
		this.businessEmail = businessEmail;
	}
	
	@JsonProperty("businessAddress")
	public Object getBusinessAddress()
	{
		return businessAddress;
	}
	
	@JsonProperty("businessAddress")
	public void setBusinessAddress(Object businessAddress)
	{
		this.businessAddress = businessAddress;
	}
	
	@JsonProperty("businessPhoneNumber")
	public Object getBusinessPhoneNumber()
	{
		return businessPhoneNumber;
	}
	
	@JsonProperty("businessPhoneNumber")
	public void setBusinessPhoneNumber(Object businessPhoneNumber)
	{
		this.businessPhoneNumber = businessPhoneNumber;
	}
	
	@JsonProperty("isBusinessSeller")
	public Boolean getIsBusinessSeller()
	{
		return isBusinessSeller;
	}
	
	@JsonProperty("isBusinessSeller")
	public void setIsBusinessSeller(Boolean isBusinessSeller)
	{
		this.isBusinessSeller = isBusinessSeller;
	}
	
	@JsonProperty("locale")
	public String getLocale()
	{
		return locale;
	}
	
	@JsonProperty("locale")
	public void setLocale(String locale)
	{
		this.locale = locale;
	}
	
	@JsonProperty("description")
	public Object getDescription()
	{
		return description;
	}
	
	@JsonProperty("description")
	public void setDescription(Object description)
	{
		this.description = description;
	}
	
	@JsonProperty("avatarUrl")
	public Object getAvatarUrl()
	{
		return avatarUrl;
	}
	
	@JsonProperty("avatarUrl")
	public void setAvatarUrl(Object avatarUrl)
	{
		this.avatarUrl = avatarUrl;
	}
	
	@JsonProperty("smallAvatarUrl")
	public Object getSmallAvatarUrl()
	{
		return smallAvatarUrl;
	}
	
	@JsonProperty("smallAvatarUrl")
	public void setSmallAvatarUrl(Object smallAvatarUrl)
	{
		this.smallAvatarUrl = smallAvatarUrl;
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
