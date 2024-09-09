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
package software.xdev.universe.requests.get_events;

import software.xdev.universe.requests.UniverseRequest;


public class GetEventsRequest implements UniverseRequest<GetEventsResponse>
{
	@Override
	public Class<GetEventsResponse> getResponseClass()
	{
		return GetEventsResponse.class;
	}
	
	public final String getQuery(final String hostId, final int limit)
	{
		return "{ \"query\": \"{"
			+ "   host(id: \\\"" + hostId + "\\\") {"
			+ "      events {"
			+ "        nodes(limit: " + limit + ") {"
			+ "          id"
			+ "          title"
			+ "          description"
			+ "          url"
			+ "        }"
			+ "    }"
			+ "  }"
			+ "}\"}";
	}
}


