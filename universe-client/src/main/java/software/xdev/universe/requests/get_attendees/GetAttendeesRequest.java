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

import software.xdev.universe.requests.UniverseRequest;


public class GetAttendeesRequest implements UniverseRequest<GetAttendeesResponse>
{
	@Override
	public Class<GetAttendeesResponse> getResponseClass()
	{
		return GetAttendeesResponse.class;
	}
	
	public final String getQuery(final String  eventId, final int limit, final int offset)
	{
		return "{ \"query\": \"{ event(id: \\\"" + eventId + "\\\") {"
			+ "    attendees {"
			+ "      nodes(limit: " + limit + " offset: " + offset + ") {"
			+ "        id"
			+ "        firstName"
			+ "        lastName"
			+ "        email"
			+ "        rate {"
			+ "          name"
			+ "        }"
			+ "        order {"
			+ "          state"
			+ "          id"
			+ "        }"
			+ "        answers {"
			+ "          question {"
			+ "            id"
			+ "            question"
			+ "            type"
			+ "          }"
			+ "          value"
			+ "        }"
			+ "      }"
			+ "    }"
			+ "  }"
			+ " } \"}";
	}
}


