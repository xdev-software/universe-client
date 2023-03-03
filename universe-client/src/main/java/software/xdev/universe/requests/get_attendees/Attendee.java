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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

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
	"lastName",
	"email",
	"rate",
	"order",
	"answers"
})
public class Attendee
{
	
	@JsonIgnore
	private final Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
	@JsonProperty("id")
	private String id;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("email")
	private String email;
	@JsonProperty("rate")
	private Rate rate;
	@JsonProperty("order")
	private Order order;
	@JsonProperty("answers")
	private List<Answer> answers;
	
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
	
	@JsonProperty("rate")
	public Rate getRate()
	{
		return rate;
	}
	
	@JsonProperty("rate")
	public void setRate(Rate rate)
	{
		this.rate = rate;
	}
	
	@JsonProperty("order")
	public Order getOrder()
	{
		return order;
	}
	
	@JsonProperty("order")
	public void setOrder(Order order)
	{
		this.order = order;
	}
	
	@JsonProperty("answers")
	public List<Answer> getAnswers()
	{
		return answers;
	}
	
	@JsonProperty("answers")
	public void setAnswers(List<Answer> answers)
	{
		this.answers = answers;
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
	
	/**
	 * @return {@code null} if no Question or answer is found
	 */
	public String getUntypedAnswerToQuestion(String question)
	{
		return getTypedAnswerToQuestion(
			new TypedQuestion<>(
				question,
				value -> value)
		);
	}
	
	/**
	 * Default questions can be found in {@link DefaultQuestions}.
	 *
	 * @return {@code null} if no Question or answer is found
	 */
	public <T> T getTypedAnswerToQuestion(TypedQuestion<T> question)
	{
		final Optional<Answer> answerForQuestion = this.getAnswers().stream().filter(
			answer -> question.getQuestionTitle().equals(answer.getQuestion().getQuestion())
		).findFirst();
		if(answerForQuestion.isEmpty())
		{
			return null;
		}
		return question.parseValue(answerForQuestion.get().getValue());
	}
	
	public static class TypedQuestion<T>
	{
		private final String questionTitle;
		private final Function<String, T> parseFunction;
		
		TypedQuestion(String questionTitle, Function<String, T> parseFunction)
		{
			this.questionTitle = questionTitle;
			this.parseFunction = parseFunction;
		}
		
		public String getQuestionTitle()
		{
			return this.questionTitle;
		}
		
		public T parseValue(String value)
		{
			return parseFunction.apply(value);
		}
	}
}
